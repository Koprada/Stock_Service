package com.emazon.stock_service.Application.handler.ArticleHandler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Domain.model.Article;

public interface IArticleHandler {

    public void saveArticle(ArticleDtoRequest articleDtoRequest);
}
