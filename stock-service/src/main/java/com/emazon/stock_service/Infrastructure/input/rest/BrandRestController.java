package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.brandDto.BrandDtoRequest;
import com.emazon.stock_service.Application.dto.brandDto.BrandDtoResponse;
import com.emazon.stock_service.Application.handler.brandHandler.BrandHandler;
import com.emazon.stock_service.Domain.model.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class BrandRestController {

    private final BrandHandler brandHandler;

    @Operation(summary = "Guardar una nueva marca")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca guardada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveBrand(@RequestBody BrandDtoRequest brandDtoRequest) {
        brandHandler.saveBrand(brandDtoRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar marcas con paginación y orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pagination.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/list")
    public ResponseEntity<Pagination<BrandDtoResponse>> listBrands(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pagination<BrandDtoResponse> pagination = brandHandler.listBrands(sortOrder, page, size);
        return ResponseEntity.ok(pagination);
    }

}
