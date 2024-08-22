package com.emazon.stock_service.Application.handler;
import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.CategoryDtoResponse;
import com.emazon.stock_service.Application.mapper.CategoryRequestMapper;
import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryService categoryService;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public void saveCategory(CategoryDtoRequest categoryDtoRequest) {
        Category category = categoryRequestMapper.toCategory(categoryDtoRequest);
        categoryService.saveCategory(category);
    }

    @Override
    public List<CategoryDtoResponse> listCategories() {
        List<Category> categories = categoryService.listCategories();
        return categories.stream()
                .map(categoryRequestMapper::toCategoryDtoResponse)
                .collect(Collectors.toList());
    }
}
