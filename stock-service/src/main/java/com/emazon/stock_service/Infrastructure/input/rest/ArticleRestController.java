package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoRequest;
import com.emazon.stock_service.Application.dto.brandDto.BrandDtoRequest;
import com.emazon.stock_service.Application.handler.ArticleHandler.ArticleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulo")
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleHandler articleHandler;


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
}
