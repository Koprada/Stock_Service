package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IBrandRepository;
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

public class BrandJpaAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBrand() {
        // Given
        Brand brand = new Brand(1L, "Samsung", "Electronics and appliances");
        BrandEntity brandEntity = new BrandEntity(1L, "Samsung", "Electronics and appliances");

        when(brandEntityMapper.brandToBrandEntity(any(Brand.class))).thenReturn(brandEntity);
        when(brandRepository.existsByNombre(anyString())).thenReturn(false);

        // When
        brandJpaAdapter.saveBrand(brand);

        // Then
        verify(brandRepository, times(1)).save(any(BrandEntity.class));
    }

    @Test
    void testListBrands() {
        // Given
        Pageable pageable = mock(Pageable.class);
        BrandEntity brandEntity = new BrandEntity(1L, "Samsung", "Electronics and appliances");
        Page<BrandEntity> page = new PageImpl<>(Collections.singletonList(brandEntity));

        when(brandRepository.findAll(pageable)).thenReturn(page);
        when(brandEntityMapper.toBrand(any(BrandEntity.class))).thenReturn(new Brand(1L, "Samsung", "Electronics and appliances"));

        // When
        Page<Brand> result = brandJpaAdapter.listBrands(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Samsung", result.getContent().get(0).getName());
    }
}
