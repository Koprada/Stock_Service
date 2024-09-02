package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticlePersistencePort {

    void saveArticle(Article article);

    boolean existsByName(String name);

    Page<Article> listArticles(Pageable pageable);
    Page<Article> listArticlesWithCustomSorting(String sortBy, String sortOrder, Pageable pageable);
}
