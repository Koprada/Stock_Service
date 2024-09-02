package com.emazon.stock_service.Application.handler.ArticleHandler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoResponse;
import com.emazon.stock_service.Application.mapper.ArticleRequestMapper;
import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Pagination<ArticleDtoResponse> listArticles(String sortBy, String sortOrder, Pageable pageable) {
        Pagination<Article> articlesPage = articleService.listArticles(sortBy, sortOrder, pageable);
        List<ArticleDtoResponse> articleDtoResponses = articlesPage.getContent().stream()
                .map(articleRequestMapper::toArticleDtoResponse)
                .collect(Collectors.toList());

        return new Pagination<>(
                articleDtoResponses,
                articlesPage.getPage(),
                articlesPage.getSize(),
                articlesPage.getTotalElements(),
                articlesPage.getTotalPages(),
                articlesPage.isLast()
        );
    }

    @Override
    public Pagination<ArticleDtoResponse> listArticles(Pageable pageable) {
        Pagination<Article> articlesPage = articleService.listArticles(pageable);
        List<ArticleDtoResponse> articleDtoResponses = articlesPage.getContent().stream()
                .map(articleRequestMapper::toArticleDtoResponse)
                .collect(Collectors.toList());

        return new Pagination<>(
                articleDtoResponses,
                articlesPage.getPage(),
                articlesPage.getSize(),
                articlesPage.getTotalElements(),
                articlesPage.getTotalPages(),
                articlesPage.isLast()
        );
    }
}