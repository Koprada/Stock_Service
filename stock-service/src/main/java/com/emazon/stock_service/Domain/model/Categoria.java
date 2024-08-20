package com.emazon.stock_service.Domain.model;

import java.util.List;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    private Long id;
    private String nombre;
    private String descripcion;

}
