package com.emazon.stock_service.Domain.api;

import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Pagination;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

    void saveArticle(Article article);
    Pagination<Article> listArticles(Pageable pageable);

    Pagination<Article> listArticles(String sortBy, String sortOrder, Pageable pageable);
}