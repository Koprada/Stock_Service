package com.emazon.stock_service.Infrastructure.input.rest;

import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
import com.emazon.stock_service.Application.handler.categoryHandler.CategoryHandler;
import com.emazon.stock_service.Domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<Pagination<CategoryDtoResponse>> listCategories(
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pagination<CategoryDtoResponse> pagination = categoryHandler.listCategories(sortOrder, page, size);
        return ResponseEntity.ok(pagination);
    }

}
