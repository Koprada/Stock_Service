package com.emazon.stock_service.Infrastructure.output.jpa.repository;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    boolean existsByName(String name);

}
