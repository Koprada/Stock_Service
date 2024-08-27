package com.emazon.stock_service.Application.handler.brandHandler;

import com.emazon.stock_service.Application.dto.brandDto.BrandDtoRequest;
import com.emazon.stock_service.Application.dto.brandDto.BrandDtoResponse;
import com.emazon.stock_service.Domain.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandHandler {

    void saveBrand(BrandDtoRequest brandDtoRequest);

    Pagination<BrandDtoResponse> listBrands(String sortOrder, int page, int size);

    Page<BrandDtoResponse> listBrands(Pageable pageable);
}
