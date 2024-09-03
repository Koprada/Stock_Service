package com.emazon.stock_service.Domain.api;

import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Pagination;
import org.springframework.data.domain.Pageable;

public interface IArticleService {

    void saveArticle(Article article);

    Pagination<Article> listArticles(Pageable pageable);

    Pagination<Article> listArticlesWithCustomSorting(String sortBy, String sortOrder, Pageable pageable);

    Pagination<Article> listArticlesSortedByCategoryName(String sortOrder, Pageable pageable);

    Pagination<Article> listArticlesByCategoryName(String categoryName, Pageable pageable);

    Pagination<Article> listArticlesSortedByBrandName(String sortOrder, Pageable pageable);

    Pagination<Article> listArticlesSortedByArticleField(String sortBy, String sortOrder, Pageable pageable);
}
