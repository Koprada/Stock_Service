package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoResponse;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
import com.emazon.stock_service.Application.handler.ArticleHandler.ArticleHandler;
import com.emazon.stock_service.Application.mapper.ArticleRequestMapper;
import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.model.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class ArticleHandlerTest {

    @InjectMocks
    private ArticleHandler articleHandler;

    @Mock
    private IArticleService articleService;

    @Mock
    private ArticleRequestMapper articleRequestMapper;

    private ArticleDtoRequest articleDtoRequest;
    private Article article;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        articleDtoRequest = new ArticleDtoRequest(
                "Auriculares Bluetooth",
                "Auriculares inalámbricos con cancelación de ruido y batería de larga duración.",
                40,
                149.99,
                1L,
                List.of(new Category(9L, "Electrónica", "Dispositivos electrónicos"),
                        new Category(10L, "Accesorios", "Accesorios para dispositivos"))
        );

        article = new Article(
                null,
                "Auriculares Bluetooth",
                "Auriculares inalámbricos con cancelación de ruido y batería de larga duración.",
                40,
                149.99,
                1L,
                List.of(new Category(9L, "Electrónica", "Dispositivos electrónicos"),
                        new Category(10L, "Accesorios", "Accesorios para dispositivos"))
        );
    }

    @Test
    void testSaveArticle() {
        // Arrange
        when(articleRequestMapper.toArticle(articleDtoRequest)).thenReturn(article);

        // Act
        articleHandler.saveArticle(articleDtoRequest);

        // Assert
        verify(articleRequestMapper).toArticle(articleDtoRequest);
        verify(articleService).saveArticle(article);
    }

    @Test
    void listArticles_ShouldReturnPaginatedResults_WhenCalled() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Pagination<Article> articlesPage = new Pagination<>(
                List.of(article),
                0,
                10,
                1,
                1,
                true
        );

        when(articleService.listArticles(pageable)).thenReturn(articlesPage);
        when(articleRequestMapper.toArticleDtoResponse(article)).thenReturn(
                new ArticleDtoResponse(
                        1L,
                        "Auriculares Bluetooth",
                        "Auriculares inalámbricos con cancelación de ruido y batería de larga duración.",
                        40,
                        149.99,
                        1L,
                        List.of()
                )
        );

        // Act
        Pagination<ArticleDtoResponse> result = articleHandler.listArticles(pageable);

        // Assert
        assertEquals(1, result.getContent().size());
        assertEquals("Auriculares Bluetooth", result.getContent().get(0).getName());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isLast());
    }

    @Test
    void listArticlesWithCustomSorting_ShouldReturnPaginatedResults_WhenCalled() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Pagination<Article> articlesPage = new Pagination<>(
                List.of(article),
                0,
                10,
                1,
                1,
                true
        );

        when(articleService.listArticlesSortedByArticleField("name", "asc", pageable)).thenReturn(articlesPage);
        when(articleRequestMapper.toArticleDtoResponse(article)).thenReturn(
                new ArticleDtoResponse(
                        1L,
                        "Auriculares Bluetooth",
                        "Auriculares inalámbricos con cancelación de ruido y batería de larga duración.",
                        40,
                        149.99,
                        1L,
                        List.of()
                )
        );

        // Act
        Pagination<ArticleDtoResponse> result = articleHandler.listArticles("name", "asc", pageable);

        // Assert
        assertEquals(1, result.getContent().size());
        assertEquals("Auriculares Bluetooth", result.getContent().get(0).getName());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getSize());
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.isLast());
    }
}
