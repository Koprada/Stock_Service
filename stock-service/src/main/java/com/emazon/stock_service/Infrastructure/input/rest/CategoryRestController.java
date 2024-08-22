package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.handler.CategoryHandler;
import com.emazon.stock_service.Domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryHandler categoryHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryDtoRequest categoryDtoRequest) {
        categoryHandler.saveCategory(categoryDtoRequest);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = categoryHandler.listCategories();
        return ResponseEntity.ok(categories);
    }
}
