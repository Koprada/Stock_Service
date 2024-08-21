package com.emazon.stock_service.Infrastructure.output.jpa.repository;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

}
