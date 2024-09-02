package com.emazon.stock_service.domain.useCase;

import com.emazon.stock_service.Application.mapper.ArticleRequestMapper;
import com.emazon.stock_service.Domain.Constants.ExceptionConstants;
import com.emazon.stock_service.Domain.exception.ArticleAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.InvalidArticleException;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.ArticlePersistencePort;
import com.emazon.stock_service.Domain.useCase.ArticleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    private ArticlePersistencePort articlePersistencePort;
    private ArticleUseCase articleUseCase;
    private final ArticleRequestMapper articleRequestMapper;

    ArticleUseCaseTest(ArticleRequestMapper articleRequestMapper) {
        this.articleRequestMapper = articleRequestMapper;
    }

    @BeforeEach
    void setUp() {
        articlePersistencePort = mock(ArticlePersistencePort.class);
        articleUseCase = new ArticleUseCase(articlePersistencePort, articleRequestMapper);
    }

    @Test
    void saveArticle_ShouldSaveSuccessfully_WhenArticleIsValid() {
        // Arrange
        Article article = new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, createValidCategories());
        when(articlePersistencePort.existsByName(article.getName())).thenReturn(false);

        // Act
        articleUseCase.saveArticle(article);

        // Assert
        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowInvalidArticleException_WhenNameIsEmpty() {
        // Arrange
        Article article = new Article(1L, "", "High-end smartphone", 10, 999.99, 1L, createValidCategories());

        // Act & Assert
        InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleUseCase.saveArticle(article));

        assertEquals(ExceptionConstants.ARTICLE_NAME_EMPTY, exception.getMessage());
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowInvalidArticleException_WhenPriceIsInvalid() {
        // Arrange
        Article article = new Article(1L, "Smartphone", "High-end smartphone", 10, -999.99, 1L, createValidCategories());

        // Act & Assert
        InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> articleUseCase.saveArticle(article));

        assertEquals(ExceptionConstants.ARTICLE_PRICE_INVALID, exception.getMessage());
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowArticleAlreadyExistsException_WhenArticleAlreadyExists() {
        // Arrange
        Article article = new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, createValidCategories());
        when(articlePersistencePort.existsByName(article.getName())).thenReturn(true);

        // Act & Assert
        ArticleAlreadyExistsException exception = assertThrows(ArticleAlreadyExistsException.class, () -> articleUseCase.saveArticle(article));

        assertEquals(ExceptionConstants.ARTICLE_ALREADY_EXISTS, exception.getMessage());
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    @Test
    void saveArticle_ShouldThrowIllegalArgumentException_WhenCategoryCountIsInvalid() {
        // Arrange
        Article article = new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, createTooManyCategories());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> articleUseCase.saveArticle(article));

        assertEquals(ExceptionConstants.ARTICLE_CATEGORY_COUNT_INVALID, exception.getMessage());
        verify(articlePersistencePort, never()).saveArticle(article);
    }

    private List<Category> createValidCategories() {
        return Arrays.asList(
                new Category(1L, "Electronics", "Devices and gadgets"),
                new Category(2L, "Smartphones", "Mobile phones")
        );
    }

    private List<Category> createTooManyCategories() {
        return Arrays.asList(
                new Category(1L, "Electronics", "Devices and gadgets"),
                new Category(2L, "Smartphones", "Mobile phones"),
                new Category(3L, "Home Appliances", "Appliances for home"),
                new Category(4L, "Gaming", "Gaming consoles and accessories") // Exceeds the allowed 3 categories
        );
    }
}