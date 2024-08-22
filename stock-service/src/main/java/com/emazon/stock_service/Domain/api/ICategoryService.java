package com.emazon.stock_service.Domain.api;

import com.emazon.stock_service.Domain.model.Category;

import java.util.List;

public interface ICategoryService {

    void saveCategory(Category category);

    List<Category> listCategories();
}
