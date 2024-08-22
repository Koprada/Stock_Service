package com.emazon.stock_service.Application.handler;
import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.mapper.CategoryRequestMapper;
import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Category> listCategories() {
        return categoryService.listCategories();
    }
}
