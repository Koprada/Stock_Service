package com.emazon.stock_service.Infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Table(name = "marca")
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la marca no puede estar vacío")
    @Size(max = 50, message = "El nombre de la marca no puede exceder los 50 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción de la marca no puede estar vacía")
    @Size(max = 120, message = "La descripción de la marca no puede exceder los 120 caracteres")
    private String descripcion;
}
