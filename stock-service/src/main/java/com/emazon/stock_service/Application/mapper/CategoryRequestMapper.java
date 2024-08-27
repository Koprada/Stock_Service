package com.emazon.stock_service.Application.mapper;


import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.categoryDto.CategoryDtoResponse;
import com.emazon.stock_service.Domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryRequestMapper {

    Category toCategory(CategoryDtoRequest categoryDtoRequest);

    CategoryDtoResponse toCategoryDtoResponse(Category category);

}
