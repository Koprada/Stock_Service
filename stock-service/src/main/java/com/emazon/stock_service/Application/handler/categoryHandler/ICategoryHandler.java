package com.emazon.stock_service.Application.handler.categoryHandler;

import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
import com.emazon.stock_service.Domain.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryHandler {

    void saveCategory(CategoryDtoRequest categoryDtoRequest);

    Pagination<CategoryDtoResponse> listCategories(String sortOrder, int page, int size);

    Page<CategoryDtoResponse> listCategories(Pageable pageable);
}
