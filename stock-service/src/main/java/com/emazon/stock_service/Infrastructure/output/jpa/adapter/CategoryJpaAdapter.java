package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Infrastructure.constant.ExceptionConstants;
import com.emazon.stock_service.Infrastructure.exception.DatabaseException;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        try {
            if (categoryRepository.existsByNombre(category.getNombre())) {
                throw new IllegalArgumentException(ExceptionConstants.CATEGORY_ALREADY_EXISTS);
            }
            categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_SAVING_CATEGORY + e.getMessage());
        }
    }

    @Override
    public Page<Category> listCategories(Pageable pageable) {
        try {
            return categoryRepository.findAll(pageable)
                    .map(categoryEntityMapper::toCategory);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_LISTING_CATEGORIES + e.getMessage());
        }
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return categoryRepository.existsByNombre(nombre);
    }
}
