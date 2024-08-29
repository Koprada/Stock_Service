package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Article;

public interface ArticlePersistencePort {
    void saveArticle(Article article);

    boolean existsByName(String name);
}
