package com.emazon.stock_service.Infrastructure.output.jpa.entity;


import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;


@Entity
@Data
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 50, message = "El nombre de la categoría no puede exceder los 50 caracteres")
    private String name;

    @NotBlank(message = "La descripción de la categoría no puede estar vacía")
    @Size(max = 90, message = "La descripción de la categoría no puede exceder los 90 caracteres")
    private String description;
}
