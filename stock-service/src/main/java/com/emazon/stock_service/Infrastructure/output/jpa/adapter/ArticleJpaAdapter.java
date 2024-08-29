package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.exception.InvalidArticleException;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.ArticlePersistencePort;
import com.emazon.stock_service.Infrastructure.constant.ExceptionConstants;
import com.emazon.stock_service.Infrastructure.exception.DatabaseException;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements ArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        try {
            if (articleRepository.existsByName(article.getName())) {
                throw new IllegalArgumentException(ExceptionConstants.ERROR_SAVING_ARTICLE + ExceptionConstants.ARTICLE_ALREADY_EXISTS);
            }
            if (article.getBrandId() == null) {
                throw new IllegalArgumentException("El artículo debe tener una marca asociada.");
            }
            validateCategories(article.getCategories());
            articleRepository.save(articleEntityMapper.articleToArticleEntity(article));
        } catch (IllegalArgumentException e) {
            throw new InvalidArticleException(e.getMessage());
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_SAVING_ARTICLE + e.getMessage());
        }
    }

    private void validateCategories(List<Category> categories) {
        if (categories == null || categories.isEmpty() || categories.size() > 3) {
            throw new IllegalArgumentException(ExceptionConstants.ARTICLE_CATEGORY_COUNT_INVALID);
        }
        if (categories.stream().distinct().count() != categories.size()) {
            throw new IllegalArgumentException(ExceptionConstants.ARTICLE_CATEGORY_DUPLICATE);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return articleRepository.existsByName(name);
    }
}
