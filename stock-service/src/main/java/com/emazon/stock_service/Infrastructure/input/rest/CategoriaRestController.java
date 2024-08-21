package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.CategoriaDtoRequest;
import com.emazon.stock_service.Application.handler.CategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaRestController {

    private final CategoryHandler categoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoriaDtoRequest categoriaDtoRequest) {
        categoryHandler.saveCategory(categoriaDtoRequest);
        return ResponseEntity.ok().build();

    }
}
