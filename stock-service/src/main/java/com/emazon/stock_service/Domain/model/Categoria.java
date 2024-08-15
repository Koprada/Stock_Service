package com.emazon.stock_service.Domain.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "categorias")
    private List<Articulo> articulos;
}
