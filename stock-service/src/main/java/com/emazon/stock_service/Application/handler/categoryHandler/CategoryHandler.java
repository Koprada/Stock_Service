package com.emazon.stock_service.Application.handler.categoryHandler;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
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
        Pageable pageable = PageRequest.of(page, size,
                "desc".equalsIgnoreCase(sortOrder) ? Sort.by(Sort.Direction.DESC, Constants.CATEGORY_NAME_FIELD) : Sort.by(Sort.Direction.ASC, Constants.CATEGORY_NAME_FIELD));

        Page<Category> categoriesPage = categoryService.listCategories(pageable);

        List<CategoryDtoResponse> categoryDtoResponses = categoriesPage.stream()
                .map(categoryRequestMapper::toCategoryDtoResponse)
                .toList();

        return new Pagination<>(
                categoryDtoResponses,
                page,
                size,
                categoriesPage.getTotalElements(),
                categoriesPage.getTotalPages(),
                !categoriesPage.hasNext()
        );
    }



    @Override
    public Page<CategoryDtoResponse> listCategories(Pageable pageable) {
        return categoryService.listCategories(pageable)
                .map(categoryRequestMapper::toCategoryDtoResponse);
    }

}