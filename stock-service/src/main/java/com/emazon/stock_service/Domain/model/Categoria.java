package com.emazon.stock_service.Domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
}
