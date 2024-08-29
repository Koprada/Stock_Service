package com.emazon.stock_service.Domain.useCase;

import com.emazon.stock_service.Domain.Constants.ExceptionConstants;
import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.exception.ArticleAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.InvalidArticleException;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.ArticlePersistencePort;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleUseCase implements IArticleService {
    private final ArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(ArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        if (article.getName() == null || article.getName().isEmpty()) {
            throw new InvalidArticleException(ExceptionConstants.ARTICLE_NAME_EMPTY);
        }
        if (article.getDescription() == null || article.getDescription().isEmpty()) {
            throw new InvalidArticleException(ExceptionConstants.ARTICLE_DESCRIPTION_EMPTY);
        }
        if (article.getPrice() <= 0) {
            throw new InvalidArticleException(ExceptionConstants.ARTICLE_PRICE_INVALID);
        }
        if (article.getAmount() < 0) {
            throw new InvalidArticleException(ExceptionConstants.ARTICLE_QUANTITY_INVALID);
        }
        validateCategory(article.getCategories());
        if (articlePersistencePort.existsByName(article.getName())) {
            throw new ArticleAlreadyExistsException(ExceptionConstants.ARTICLE_ALREADY_EXISTS);
        }
        articlePersistencePort.saveArticle(article);
    }

    private void validateCategory(List<Category> categories) {
        if (categories == null || categories.isEmpty() || categories.size() > 3) {
            throw new IllegalArgumentException(ExceptionConstants.ARTICLE_CATEGORY_COUNT_INVALID);
        }

        Set<Long> categoryIds = categories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

        if (categoryIds.size() != categories.size()) {
            throw new IllegalArgumentException(ExceptionConstants.ARTICLE_CATEGORY_DUPLICATE);
        }
    }
}