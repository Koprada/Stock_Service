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
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cantidad;
    private Long precio;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToMany
    @JoinTable(
            name = "Articulo_Categoria",
            joinColumns = @JoinColumn(name = "id_articulo"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

}
