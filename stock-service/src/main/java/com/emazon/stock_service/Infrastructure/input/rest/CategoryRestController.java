package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
import com.emazon.stock_service.Application.handler.categoryHandler.CategoryHandler;
import com.emazon.stock_service.Domain.model.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryHandler categoryHandler;

    @Operation(summary = "Guardar una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría guardada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryDtoRequest categoryDtoRequest) {
        categoryHandler.saveCategory(categoryDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar categorías con paginación y orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagination.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/list")
    public ResponseEntity<Pagination<CategoryDtoResponse>> listCategories(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pagination<CategoryDtoResponse> pagination = categoryHandler.listCategories(sortOrder, page, size);
        return ResponseEntity.ok(pagination);
    }
}
