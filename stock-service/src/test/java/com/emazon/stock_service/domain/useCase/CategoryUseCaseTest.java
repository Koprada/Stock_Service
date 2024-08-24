import com.emazon.stock_service.Domain.api.ICategoryService;
import com.emazon.stock_service.Domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.CategoryNotFoundException;
import com.emazon.stock_service.Domain.exception.InvalidCategoryException;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.spi.CategoryPersistencePort;
import com.emazon.stock_service.Domain.useCase.CategoryUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryUseCaseTest {

    private final CategoryPersistencePort categoryPersistencePort = mock(CategoryPersistencePort.class);
    private final ICategoryService categoryService = new CategoryUseCase(categoryPersistencePort);

    @Test
    void testSaveCategory() {
        Category category = new Category(1L, "Electronics", "Devices and gadgets");

        when(categoryPersistencePort.existsByNombre(category.getNombre())).thenReturn(false);

        categoryService.saveCategory(category);

        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    void testSaveCategoryThrowsExceptionWhenCategoryExists() {
        Category category = new Category(1L, "Electronics", "Devices and gadgets");

        when(categoryPersistencePort.existsByNombre(category.getNombre())).thenReturn(true);

        Exception exception = assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("La categoría ya existe", exception.getMessage());
    }

    @Test
    void testListCategories() {
        Category category = new Category(1L, "Electronics", "Devices and gadgets");
        Page<Category> page = new PageImpl<>(Collections.singletonList(category), PageRequest.of(0, 10), 1);

        when(categoryPersistencePort.listCategories(any(Pageable.class))).thenReturn(page);

        Page<Category> result = categoryService.listCategories(PageRequest.of(0, 10));

        assertFalse(result.isEmpty());
        assertEquals("Electronics", result.getContent().get(0).getNombre());
    }

    @Test
    void testListCategoriesThrowsExceptionWhenNoCategories() {
        Page<Category> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        when(categoryPersistencePort.listCategories(any(Pageable.class))).thenReturn(page);

        Exception exception = assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.listCategories(PageRequest.of(0, 10));
        });

        assertEquals("No se encontraron categorías", exception.getMessage());
    }

    @Test
    void testSaveCategoryThrowsExceptionWhenInvalidCategory() {
        // Simulamos un caso en el que la categoría es inválida
        Category invalidCategory = new Category(null, "", ""); // Nombre vacío

        // Supongamos que deberíamos lanzar una InvalidCategoryException si el nombre está vacío
        Exception exception = assertThrows(InvalidCategoryException.class, () -> {
            categoryService.saveCategory(invalidCategory);
        });

        assertEquals("El nombre de la categoría no puede estar vacío", exception.getMessage());
    }
}
