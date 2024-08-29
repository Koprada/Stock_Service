package com.emazon.stock_service.domain.useCase;

import com.emazon.stock_service.Domain.api.IBrandService;
import com.emazon.stock_service.Domain.exception.BrandAlreadyExistsException;
import com.emazon.stock_service.Domain.exception.BrandNotFoundException;
import com.emazon.stock_service.Domain.exception.InvalidBrandException;
import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Domain.spi.BrandPersistencePort;
import com.emazon.stock_service.Domain.useCase.BrandUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BrandUseCaseTest {

    private final BrandPersistencePort brandPersistencePort = mock(BrandPersistencePort.class);
    private final IBrandService brandService = new BrandUseCase(brandPersistencePort);

    @Test
    void testSaveBrand() {
        Brand brand = new Brand(1L, "Samsung", "Electronics and appliances");

        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(false);

        brandService.saveBrand(brand);

        verify(brandPersistencePort).saveBrand(brand);
    }

    @Test
    void testSaveBrandThrowsExceptionWhenBrandExists() {
        Brand brand = new Brand(1L, "Samsung", "Electronics and appliances");

        when(brandPersistencePort.existsByName(brand.getName())).thenReturn(true);

        Exception exception = assertThrows(BrandAlreadyExistsException.class, () -> {
            brandService.saveBrand(brand);
        });

        assertEquals("La marca ya existe", exception.getMessage());
    }

    @Test
    void testListBrands() {
        Brand brand = new Brand(1L, "Samsung", "Electronics and appliances");
        Page<Brand> page = new PageImpl<>(Collections.singletonList(brand), PageRequest.of(0, 10), 1);

        when(brandPersistencePort.listBrands(any(Pageable.class))).thenReturn(page);

        Page<Brand> result = brandService.listBrands(PageRequest.of(0, 10));

        assertFalse(result.isEmpty());
        assertEquals("Samsung", result.getContent().get(0).getName());
    }

    @Test
    void testListBrandsThrowsExceptionWhenNoBrands() {
        Page<Brand> page = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 10), 0);

        when(brandPersistencePort.listBrands(any(Pageable.class))).thenReturn(page);

        Exception exception = assertThrows(BrandNotFoundException.class, () -> {
            brandService.listBrands(PageRequest.of(0, 10));
        });

        assertEquals("No se encontraron marcas", exception.getMessage());
    }

    @Test
    void testSaveBrandThrowsExceptionWhenInvalidBrand() {
        // Simulamos un caso en el que la marca es inválida
        Brand invalidBrand = new Brand(null, "", ""); // Nombre vacío

        // Supongamos que deberíamos lanzar una InvalidBrandException si el nombre está vacío
        Exception exception = assertThrows(InvalidBrandException.class, () -> {
            brandService.saveBrand(invalidBrand);
        });

        assertEquals("El nombre de la marca no puede estar vacío", exception.getMessage());
    }
}
