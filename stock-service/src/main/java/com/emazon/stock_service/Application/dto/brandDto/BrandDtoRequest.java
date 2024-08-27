package com.emazon.stock_service.Application.dto.brandDto;

import lombok.Data;

@Data
public class BrandDtoRequest {

    private String nombre;
    private String descripcion;

    public BrandDtoRequest() {
    }

    public BrandDtoRequest(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
