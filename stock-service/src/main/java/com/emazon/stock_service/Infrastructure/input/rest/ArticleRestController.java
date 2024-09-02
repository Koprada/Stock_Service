package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoResponse;
import com.emazon.stock_service.Application.dto.brandDto.BrandDtoRequest;
import com.emazon.stock_service.Application.handler.ArticleHandler.ArticleHandler;
import com.emazon.stock_service.Domain.model.Pagination;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IArticleRepository;
import com.emazon.stock_service.Infrastructure.output.jpa.specification.ArticleSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulo")
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleHandler articleHandler;
    private final IArticleRepository articleRepository;


    @Operation(summary = "Guardar un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@RequestBody ArticleDtoRequest articleDtoRequest) {
        articleHandler.saveArticle(articleDtoRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar artículos con paginación y ordenamiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículos listados exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/list")
    public ResponseEntity<Page<ArticleEntity>> listArticles(
            @RequestParam String sortBy,
            @RequestParam String sortOrder,
            @RequestParam int page,
            @RequestParam int size) {
        Specification<ArticleEntity> specification = ArticleSpecification.withCustomSorting(sortBy, sortOrder);
        Pageable pageable = PageRequest.of(page, size);
        Page<ArticleEntity> result = articleRepository.findAll(specification, pageable);

        result.forEach(article -> article.getCategories().forEach(category -> {
            category.setDescription(null);
        }));

        return ResponseEntity.ok(result);
    }
}