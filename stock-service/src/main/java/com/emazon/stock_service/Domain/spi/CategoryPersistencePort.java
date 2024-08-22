package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Category;

import java.util.List;

public interface CategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> listCategories();
}
