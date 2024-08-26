package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Domain.spi.BrandPersistencePort;
import com.emazon.stock_service.Infrastructure.constant.ExceptionConstants;
import com.emazon.stock_service.Infrastructure.exception.DatabaseException;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class BrandJpaAdapter implements BrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        try {
            if (brandRepository.existsByNombre(brand.getNombre())) {
                throw new IllegalArgumentException(ExceptionConstants.BRAND_ALREADY_EXISTS);
            }
            brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_SAVING_BRAND + e.getMessage());
        }
    }

    @Override
    public Page<Brand> listBrands(Pageable pageable) {
        try {
            return brandRepository.findAll(pageable)
                    .map(brandEntityMapper::toBrand);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_LISTING_BRANDS + e.getMessage());
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return brandRepository.existsByNombre(nombre);
    }

}
