package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.CategoryDtoResponse;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryDtoRequest categoryDtoRequest);

    List<CategoryDtoResponse> listCategories();
}
