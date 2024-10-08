package com.emazon.stock_service.Domain.useCase;

import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.Constants.ExceptionConstants;
import com.emazon.stock_service.Domain.exception.CategoryNotFoundException;
import com.emazon.stock_service.Domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.InvalidCategoryException;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CategoryUseCase implements ICategoryService {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new InvalidCategoryException(ExceptionConstants.CATEGORY_NAME_EMPTY);
        }
        if (category.getName().length() > 50) {
            throw new InvalidCategoryException(ExceptionConstants.CATEGORY_NAME_TOO_LONG);
        }
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new InvalidCategoryException(ExceptionConstants.CATEGORY_DESCRIPTION_EMPTY);
        }
        if (category.getDescription().length() > 90) {
            throw new InvalidCategoryException(ExceptionConstants.CATEGORY_DESCRIPTION_TOO_LONG);
        }
        if (categoryPersistencePort.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException(ExceptionConstants.CATEGORY_ALREADY_EXISTS);
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Page<Category> listCategories(Pageable pageable) {
        Page<Category> categories = categoryPersistencePort.listCategories(pageable);
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException(ExceptionConstants.CATEGORY_NOT_FOUND);
        }
        return categories;
    }
}
