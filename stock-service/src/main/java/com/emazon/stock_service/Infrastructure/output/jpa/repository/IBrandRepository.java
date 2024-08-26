package com.emazon.stock_service.Infrastructure.output.jpa.repository;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    boolean existsByNombre(String nombre);
}
