package com.emazon.stock_service.Application.handler.ArticleHandler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoResponse;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
import com.emazon.stock_service.Application.handler.categoryHandler.CategoryHandler;
import com.emazon.stock_service.Application.mapper.ArticleRequestMapper;
import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final IArticleService articleService;
    private final ArticleRequestMapper articleRequestMapper;
    private final CategoryHandler categoryHandler;

    @Override
    public void saveArticle(ArticleDtoRequest articleDtoRequest) {
        Article article = articleRequestMapper.toArticle(articleDtoRequest);
        articleService.saveArticle(article);
    }

    @Override
    public Pagination<ArticleDtoResponse> listArticles(String sortBy, String sortOrder, Pageable pageable) {
        Pagination<Article> articlesPage;

        if ("category.name".equalsIgnoreCase(sortBy)) {
            articlesPage = articleService.listArticlesSortedByCategoryName(sortOrder, pageable);
        } else if ("brand.name".equalsIgnoreCase(sortBy)) {
            articlesPage = articleService.listArticlesSortedByBrandName(sortOrder, pageable);
        } else {
            articlesPage = articleService.listArticlesSortedByArticleField(sortBy, sortOrder, pageable);
        }

        List<ArticleDtoResponse> articleDtoResponses = articlesPage.getContent().stream()
                .map(articleRequestMapper::toArticleDtoResponse)
                .toList();

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
                .toList();

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
