package com.emazon.stock_service.Application.dto;

import lombok.Data;

@Data
public class CategoryDtoRequest {

    private String nombre;
    private String descripcion;

    public CategoryDtoRequest() {
    }

    public CategoryDtoRequest(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
