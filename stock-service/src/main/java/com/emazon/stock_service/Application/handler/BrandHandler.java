package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.BrandDtoRequest;
import com.emazon.stock_service.Application.dto.BrandDtoResponse;
import com.emazon.stock_service.Application.mapper.BrandRequestMapper;
import com.emazon.stock_service.Domain.Constants.Constants;
import com.emazon.stock_service.Domain.api.IBrandService;
import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BrandHandler implements IBrandHandler {

    private final IBrandService brandService;
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public void saveBrand(BrandDtoRequest brandDtoRequest) {
        Brand brand = brandRequestMapper.toBrand(brandDtoRequest);
        brandService.saveBrand(brand);
    }
    @Override
    public Pagination<BrandDtoResponse> listBrands(String sortOrder, int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                "desc".equalsIgnoreCase(sortOrder) ? Sort.by(Sort.Direction.DESC, Constants.BRAND_NAME_FIELD) : Sort.by(Sort.Direction.ASC, Constants.BRAND_NAME_FIELD));

        Page<Brand> brandsPage = brandService.listBrands(pageable);

        List<BrandDtoResponse> brandDtoResponses = brandsPage.stream()
                .map(brandRequestMapper::toBrandDtoResponse)
                .toList();

        return new Pagination<>(
                brandDtoResponses,
                page,
                size,
                brandsPage.getTotalElements(),
                brandsPage.getTotalPages(),
                !brandsPage.hasNext()
        );
    }

    @Override
    public Page<BrandDtoResponse> listBrands(Pageable pageable) {
        return brandService.listBrands(pageable)
                .map(brandRequestMapper::toBrandDtoResponse);
    }



}
