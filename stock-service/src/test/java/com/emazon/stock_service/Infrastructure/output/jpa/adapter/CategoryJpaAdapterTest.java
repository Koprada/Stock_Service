package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CategoryJpaAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        // Given
        Category category = new Category(1L, "Electronics", "Devices and gadgets");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics", "Devices and gadgets");

        when(categoryEntityMapper.categoryToCategoryEntity(any(Category.class))).thenReturn(categoryEntity);
        when(categoryRepository.existsByName(anyString())).thenReturn(false);

        // When
        categoryJpaAdapter.saveCategory(category);

        // Then
        verify(categoryRepository, times(1)).save(any(CategoryEntity.class)); // Usa any() en lugar de any(CategoryEntity.class)
    }

    @Test
    void testListCategories() {
        // Given
        Pageable pageable = mock(Pageable.class);
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics", "Devices and gadgets");
        Page<CategoryEntity> page = new PageImpl<>(Collections.singletonList(categoryEntity));

        when(categoryRepository.findAll(pageable)).thenReturn(page);
        when(categoryEntityMapper.toCategory(any(CategoryEntity.class))).thenReturn(new Category(1L, "Electronics", "Devices and gadgets"));

        // When
        Page<Category> result = categoryJpaAdapter.listCategories(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Electronics", result.getContent().get(0).getName());
    }
}
