package com.emazon.stock_service.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoResponse {

    private Long id;
    private String nombre;
    private String descripcion;

}
