package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Domain.model.Category;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryDtoRequest categoryDtoRequest);

    List<Category> listCategories();
}
