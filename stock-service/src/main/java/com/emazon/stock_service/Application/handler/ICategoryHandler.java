package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.CategoryDtoResponse;
import com.emazon.stock_service.Domain.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryDtoRequest categoryDtoRequest);

    Pagination<CategoryDtoResponse> listCategories(String sortOrder, int page, int size);

    Page<CategoryDtoResponse> listCategories(Pageable pageable);
}
