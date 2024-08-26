package com.emazon.stock_service.Domain.useCase;

import com.emazon.stock_service.Domain.Constants.ExceptionConstants;
import com.emazon.stock_service.Domain.api.IBrandService;
import com.emazon.stock_service.Domain.exception.BrandAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.BrandNotFoundException;
import com.emazon.stock_service.Domain.exception.InvalidBrandException;
import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Domain.spi.BrandPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BrandUseCase implements IBrandService {

    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand.getNombre() == null || brand.getNombre().isEmpty()) {
            throw new InvalidBrandException(ExceptionConstants.BRAND_NAME_EMPTY);
        }
        if (brand.getNombre().length() > 50) {
            throw new InvalidBrandException(ExceptionConstants.BRAND_NAME_TOO_LONG);
        }
        if (brand.getDescripcion() == null || brand.getDescripcion().isEmpty()) {
            throw new InvalidBrandException(ExceptionConstants.BRAND_DESCRIPTION_EMPTY);
        }
        if (brand.getDescripcion().length() > 120) {
            throw new InvalidBrandException(ExceptionConstants.BRAND_DESCRIPTION_TOO_LONG);
        }
        if (brandPersistencePort.existsByNombre(brand.getNombre())) {
            throw new BrandAlreadyExistsException(ExceptionConstants.BRAND_ALREADY_EXISTS);
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Page<Brand> listBrands(Pageable pageable) {
        Page<Brand> brands = brandPersistencePort.listBrands(pageable);
        if (brands.isEmpty()) {
            throw new BrandNotFoundException(ExceptionConstants.BRAND_NOT_FOUND);
        }
        return brands;
    }

}
