package com.emazon.stock_service.Application.handler;
import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.CategoryDtoResponse;
import com.emazon.stock_service.Application.mapper.CategoryRequestMapper;
import com.emazon.stock_service.Domain.Constants.Constants;
import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.model.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Pagination<CategoryDtoResponse> listCategories(String sortOrder, int page, int size) {
        return new Pagination<>(
                categoryService.listCategories(
                                PageRequest.of(page, size,
                                        "desc".equalsIgnoreCase(sortOrder) ? Sort.by(Sort.Direction.DESC, Constants.CATEGORY_NAME_FIELD) : Sort.by(Sort.Direction.ASC, Constants.CATEGORY_NAME_FIELD))
                        ).stream()
                        .map(categoryRequestMapper::toCategoryDtoResponse)
                        .toList(),
                page,
                size,
                categoryService.listCategories(
                        PageRequest.of(page, size,
                                "desc".equalsIgnoreCase(sortOrder) ? Sort.by(Sort.Direction.DESC, Constants.CATEGORY_NAME_FIELD) : Sort.by(Sort.Direction.ASC, Constants.CATEGORY_NAME_FIELD))
                ).getTotalElements(),
                categoryService.listCategories(
                        PageRequest.of(page, size,
                                "desc".equalsIgnoreCase(sortOrder) ? Sort.by(Sort.Direction.DESC, Constants.CATEGORY_NAME_FIELD) : Sort.by(Sort.Direction.ASC, Constants.CATEGORY_NAME_FIELD))
                ).getTotalPages(),
                !categoryService.listCategories(
                        PageRequest.of(page, size,
                                "desc".equalsIgnoreCase(sortOrder) ? Sort.by(Sort.Direction.DESC, Constants.CATEGORY_NAME_FIELD) : Sort.by(Sort.Direction.ASC, Constants.CATEGORY_NAME_FIELD))
                ).hasNext()
        );
    }

    @Override
    public Page<CategoryDtoResponse> listCategories(Pageable pageable) {
        return categoryService.listCategories(pageable)
                .map(categoryRequestMapper::toCategoryDtoResponse);
    }

}