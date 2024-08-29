package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandPersistencePort {

    void saveBrand(Brand brand);

    Page<Brand> listBrands(Pageable pageable);

    boolean existsByName(String name);
}
