package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.CategoryDtoRequest;
import com.emazon.stock_service.Application.dto.CategoryDtoResponse;
import com.emazon.stock_service.Application.handler.CategoryHandler;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.model.Pagination;
import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Application.mapper.CategoryRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryHandlerTest {

    @InjectMocks
    private CategoryHandler categoryHandler;

    @Mock
    private ICategoryService categoryService;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        // Crear instancia de CategoryDtoRequest
        CategoryDtoRequest categoryDtoRequest = new CategoryDtoRequest();
        categoryDtoRequest.setNombre("Electronics");
        categoryDtoRequest.setDescripcion("Devices and gadgets");

        // Crear una instancia de Category
        Category category = new Category(1L, "Electronics", "Devices and gadgets");

        // Configurar el mapper
        when(categoryRequestMapper.toCategory(categoryDtoRequest)).thenReturn(category);

        // Ejecutar el método a probar
        categoryHandler.saveCategory(categoryDtoRequest);

        // Verificar la llamada al servicio
        verify(categoryService, times(1)).saveCategory(category);
    }

    @Test
    public void testListCategories() {
        // Crear datos de prueba
        CategoryDtoResponse categoryDtoResponse = new CategoryDtoResponse(1L, "Electronics", "Devices and gadgets");
        Category category = new Category(1L, "Electronics", "Devices and gadgets");
        Page<Category> categoryPage = new PageImpl<>(Collections.singletonList(category), PageRequest.of(0, 1), 1);

        // Configurar el mock del mapper
        when(categoryRequestMapper.toCategoryDtoResponse(category)).thenReturn(categoryDtoResponse);

        // Configurar el mock del servicio
        when(categoryService.listCategories(any(Pageable.class))).thenReturn(categoryPage);

        // Ejecutar el método a probar
        Pagination<CategoryDtoResponse> result = categoryHandler.listCategories("asc", 0, 1);

        // Verificar que listCategories del servicio se ha llamado solo una vez
        verify(categoryService, times(1)).listCategories(any(Pageable.class));

        // Verificar resultados
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Electronics", result.getContent().get(0).getNombre());
    }


}
