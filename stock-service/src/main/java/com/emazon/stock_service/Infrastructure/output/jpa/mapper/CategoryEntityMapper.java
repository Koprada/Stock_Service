package com.emazon.stock_service.Infrastructure.output.jpa.mapper;


import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CategoryEntityMapper {

    CategoryEntity categoryToCategoryEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);

}
