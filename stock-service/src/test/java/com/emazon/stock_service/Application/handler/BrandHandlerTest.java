package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.BrandDtoRequest;
import com.emazon.stock_service.Application.dto.BrandDtoResponse;
import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Domain.model.Pagination;
import com.emazon.stock_service.Domain.api.IBrandService;
import com.emazon.stock_service.Application.mapper.BrandRequestMapper;
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

public class BrandHandlerTest {

    @InjectMocks
    private BrandHandler brandHandler;

    @Mock
    private IBrandService brandService;

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveBrand() {
        // Crear instancia de BrandDtoRequest
        BrandDtoRequest brandDtoRequest = new BrandDtoRequest();
        brandDtoRequest.setNombre("Samsung");
        brandDtoRequest.setDescripcion("Electronics and appliances");

        // Crear una instancia de Brand
        Brand brand = new Brand(1L, "Samsung", "Electronics and appliances");

        // Configurar el mapper
        when(brandRequestMapper.toBrand(brandDtoRequest)).thenReturn(brand);

        // Ejecutar el método a probar
        brandHandler.saveBrand(brandDtoRequest);

        // Verificar la llamada al servicio
        verify(brandService, times(1)).saveBrand(brand);
    }

    @Test
    public void testListBrands() {
        // Crear datos de prueba
        BrandDtoResponse brandDtoResponse = new BrandDtoResponse(1L, "Samsung", "Electronics and appliances");
        Brand brand = new Brand(1L, "Samsung", "Electronics and appliances");
        Page<Brand> brandPage = new PageImpl<>(Collections.singletonList(brand), PageRequest.of(0, 1), 1);

        // Configurar el mock del mapper
        when(brandRequestMapper.toBrandDtoResponse(brand)).thenReturn(brandDtoResponse);

        // Configurar el mock del servicio
        when(brandService.listBrands(any(Pageable.class))).thenReturn(brandPage);

        // Ejecutar el método a probar
        Pagination<BrandDtoResponse> result = brandHandler.listBrands("asc", 0, 1);

        // Verificar que listBrands del servicio se ha llamado solo una vez
        verify(brandService, times(1)).listBrands(any(Pageable.class));

        // Verificar resultados
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Samsung", result.getContent().get(0).getNombre());
    }
}
