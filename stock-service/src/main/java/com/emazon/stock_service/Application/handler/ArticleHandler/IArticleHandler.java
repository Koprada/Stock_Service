package com.emazon.stock_service.Application.handler.ArticleHandler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoResponse;
import com.emazon.stock_service.Domain.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IArticleHandler {
    void saveArticle(ArticleDtoRequest articleDtoRequest);

    Pagination<ArticleDtoResponse> listArticles(String sortBy, String sortOrder, Pageable pageable);
    Pagination<ArticleDtoResponse> listArticles(Pageable pageable);
}
