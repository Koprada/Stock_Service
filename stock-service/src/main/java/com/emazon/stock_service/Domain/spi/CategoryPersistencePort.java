package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryPersistencePort {

    void saveCategory(Category category);

    Page<Category> listCategories(Pageable pageable);

    boolean existsByName(String name);
}
