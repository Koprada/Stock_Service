package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements CategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.existsByNombre(category.getNombre())) {
            throw new IllegalArgumentException("El nombre de la categoría ya existe");
        }
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryEntityMapper::toCategory)
                .collect(Collectors.toList());
    }
}