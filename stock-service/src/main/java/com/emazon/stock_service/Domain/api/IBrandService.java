package com.emazon.stock_service.Domain.api;

import com.emazon.stock_service.Domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {

    void saveBrand(Brand brand);

    Page<Brand> listBrands(Pageable pageable);
}
