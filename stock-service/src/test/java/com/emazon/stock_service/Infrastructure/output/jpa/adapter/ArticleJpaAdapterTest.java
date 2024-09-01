package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.exception.InvalidArticleException;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Infrastructure.constant.ExceptionConstants;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleJpaAdapterTest {

    private IArticleRepository articleRepository;
    private ArticleEntityMapper articleEntityMapper;
    private ArticleJpaAdapter articleJpaAdapter;

    @BeforeEach
    void setUp() {
        articleRepository = mock(IArticleRepository.class);
        articleEntityMapper = mock(ArticleEntityMapper.class);
        articleJpaAdapter = new ArticleJpaAdapter(articleRepository, articleEntityMapper);
    }

    @Test
    void saveArticle_ShouldThrowInvalidArticleException_WhenArticleAlreadyExists() {
        // Arrange
        Article article = createValidArticle();
        when(articleRepository.existsByName(article.getName())).thenReturn(true);

        // Act & Assert
        InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> {
            articleJpaAdapter.saveArticle(article);
        });

        assertTrue(exception.getMessage().contains(ExceptionConstants.ARTICLE_ALREADY_EXISTS));
        verify(articleRepository, never()).save(any(ArticleEntity.class));
    }

    @Test
    void saveArticle_ShouldThrowInvalidArticleException_WhenArticleHasNoBrand() {
        // Arrange
        Article article = createValidArticle();
        article.setBrandId(null);
        when(articleRepository.existsByName(article.getName())).thenReturn(false);

        // Act & Assert
        InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> {
            articleJpaAdapter.saveArticle(article);
        });

        assertEquals("El artículo debe tener una marca asociada.", exception.getMessage());
        verify(articleRepository, never()).save(any(ArticleEntity.class));
    }

    @Test
    void saveArticle_ShouldThrowInvalidArticleException_WhenCategoriesAreInvalid() {
        // Arrange
        Article article = createArticleWithTooManyCategories();
        when(articleRepository.existsByName(article.getName())).thenReturn(false);

        // Act & Assert
        InvalidArticleException exception = assertThrows(InvalidArticleException.class, () -> {
            articleJpaAdapter.saveArticle(article);
        });

        assertEquals(ExceptionConstants.ARTICLE_CATEGORY_COUNT_INVALID, exception.getMessage());
        verify(articleRepository, never()).save(any(ArticleEntity.class));
    }

    @Test
    void saveArticle_ShouldSaveSuccessfully_WhenArticleIsValid() {
        // Arrange
        Article article = createValidArticle();
        ArticleEntity articleEntity = new ArticleEntity();
        when(articleRepository.existsByName(article.getName())).thenReturn(false);
        when(articleEntityMapper.articleToArticleEntity(article)).thenReturn(articleEntity);

        // Act
        articleJpaAdapter.saveArticle(article);

        // Assert
        verify(articleRepository, times(1)).save(articleEntity);
    }

    @Test
    void existsByName_ShouldReturnTrue_WhenArticleExists() {
        // Arrange
        String articleName = "Smartphone";
        when(articleRepository.existsByName(articleName)).thenReturn(true);

        // Act
        boolean exists = articleJpaAdapter.existsByName(articleName);

        // Assert
        assertTrue(exists);
        verify(articleRepository, times(1)).existsByName(articleName);
    }

    @Test
    void existsByName_ShouldReturnFalse_WhenArticleDoesNotExist() {
        // Arrange
        String articleName = "Smartphone";
        when(articleRepository.existsByName(articleName)).thenReturn(false);

        // Act
        boolean exists = articleJpaAdapter.existsByName(articleName);

        // Assert
        assertFalse(exists);
        verify(articleRepository, times(1)).existsByName(articleName);
    }

    // Métodos auxiliares para crear objetos Article
    private Article createValidArticle() {
        return new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, createValidCategories());
    }

    private List<Category> createValidCategories() {
        return Arrays.asList(
                new Category(1L, "Electronics", "Devices and gadgets"),
                new Category(2L, "Smartphones", "Mobile phones")
        );
    }

    private Article createArticleWithTooManyCategories() {
        return new Article(1L, "Smartphone", "High-end smartphone", 10, 999.99, 1L, Arrays.asList(
                new Category(1L, "Electronics", "Devices and gadgets"),
                new Category(2L, "Smartphones", "Mobile phones"),
                new Category(3L, "Home Appliances", "Appliances for home"),
                new Category(4L, "Gaming", "Gaming consoles and accessories")
        ));
    }
}
