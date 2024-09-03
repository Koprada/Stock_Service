package com.emazon.stock_service.domain.useCase;

import com.emazon.stock_service.Domain.Constants.ExceptionConstants;
import com.emazon.stock_service.Domain.exception.ArticleAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.InvalidArticleException;
import com.emazon.stock_service.Domain.exception.ArticleNotFoundException;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.model.Pagination;
import com.emazon.stock_service.Domain.spi.ArticlePersistencePort;
import com.emazon.stock_service.Domain.useCase.ArticleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    private ArticlePersistencePort articlePersistencePort;
    private ArticleUseCase articleUseCase;

    @BeforeEach
    void setUp() {
        articlePersistencePort = mock(ArticlePersistencePort.class);
        articleUseCase = new ArticleUseCase(articlePersistencePort);
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

    @Test
    void listArticles_ShouldReturnPaginatedResults_WhenCalled() {
        // Arrange
        List<Article> articles = Arrays.asList(
                new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, createValidCategories()),
                new Article(2L, "Laptop", "Gaming laptop", 5, 1299.99, 2L, createValidCategories())
        );
        Pagination<Article> expectedPagination = new Pagination<>(
                articles, 0, 10, 2, 1, true
        );

        when(articlePersistencePort.listArticles(any(Pageable.class))).thenReturn(expectedPagination);

        // Act
        Pagination<Article> result = articleUseCase.listArticles(Pageable.ofSize(10));

        // Assert
        assertNotNull(result);
        assertEquals(expectedPagination.getContent(), result.getContent());
        assertEquals(expectedPagination.getPage(), result.getPage());
        assertEquals(expectedPagination.getSize(), result.getSize());
        assertEquals(expectedPagination.getTotalElements(), result.getTotalElements());
        assertEquals(expectedPagination.getTotalPages(), result.getTotalPages());
        assertEquals(expectedPagination.isLast(), result.isLast());
    }


    @Test
    void listArticlesWithCustomSorting_ShouldReturnPaginatedResults_WhenCalled() {
        // Arrange
        List<Article> articles = Arrays.asList(
                new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, createValidCategories()),
                new Article(2L, "Laptop", "Gaming laptop", 5, 1299.99, 2L, createValidCategories())
        );
        Pagination<Article> expectedPagination = new Pagination<>(
                articles, 0, 10, 2, 1, true
        );

        when(articlePersistencePort.listArticlesWithCustomSorting(anyString(), anyString(), any(Pageable.class))).thenReturn(expectedPagination);

        // Act
        Pagination<Article> result = articleUseCase.listArticlesWithCustomSorting("name", "asc", Pageable.ofSize(10));

        // Assert
        assertEquals(expectedPagination, result);
    }


    @Test
    void listArticlesWithCustomSorting_ShouldThrowArticleNotFoundException_WhenErrorOccurs() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        when(articlePersistencePort.listArticlesWithCustomSorting("name", "asc", pageable))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        ArticleNotFoundException exception = assertThrows(ArticleNotFoundException.class,
                () -> articleUseCase.listArticlesWithCustomSorting("name", "asc", pageable));

        assertEquals(ExceptionConstants.ARTICLE_NOT_FOUND + "Database error", exception.getMessage());
        verify(articlePersistencePort, times(1)).listArticlesWithCustomSorting("name", "asc", pageable);
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
