package com.emazon.stock_service.Infrastructure.output.jpa.repository;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByNombre(String nombre);

}