package com.emazon.stock_service.Infrastructure.output.jpa.repository;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {

    boolean existsByName(String name);

    Page<ArticleEntity> findAll(Pageable pageable);
}