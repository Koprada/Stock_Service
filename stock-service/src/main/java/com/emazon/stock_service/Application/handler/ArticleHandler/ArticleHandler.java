package com.emazon.stock_service.Application.handler.ArticleHandler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.mapper.ArticleRequestMapper;
import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final IArticleService articleService;
    private final ArticleRequestMapper articleRequestMapper;

    @Override
    public void saveArticle(ArticleDtoRequest articleDtoRequest) {
        Article article = articleRequestMapper.toArticle(articleDtoRequest);
        articleService.saveArticle(article);
    }
}
