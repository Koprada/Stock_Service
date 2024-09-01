package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.handler.ArticleHandler.ArticleHandler;
import com.emazon.stock_service.Application.handler.brandHandler.BrandHandler;
import com.emazon.stock_service.Application.mapper.ArticleRequestMapper;
import com.emazon.stock_service.Application.mapper.BrandRequestMapper;
import com.emazon.stock_service.Domain.api.IArticleService;
import com.emazon.stock_service.Domain.api.IBrandService;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArticleHandlerTest {

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

}
