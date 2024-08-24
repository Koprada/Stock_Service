package com.emazon.stock_service.Domain.useCase;

import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CategoryUseCase implements ICategoryService {

    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getNombre() == null || category.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        if (category.getNombre().length() > 50) {
            throw new IllegalArgumentException("El nombre de la categoría no puede exceder los 50 caracteres");
        }
        if (category.getDescripcion() == null || category.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía");
        }
        if (category.getDescripcion().length() > 90) {
            throw new IllegalArgumentException("La descripción de la categoría no puede exceder los 90 caracteres");
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Page<Category> listCategories(Pageable pageable) {
        return categoryPersistencePort.listCategories(pageable);
    }

}