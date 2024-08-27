package com.emazon.stock_service.Application.dto.brandDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDtoResponse {

        private Long id;
        private String nombre;
        private String descripcion;
}
