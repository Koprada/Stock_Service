package com.emazon.stock_service.Infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
}
