package com.emazon.stock_service.Domain.api;

import com.emazon.stock_service.Domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICategoryService {

    void saveCategory(Category category);

    Page<Category> listCategories(Pageable pageable);
}
