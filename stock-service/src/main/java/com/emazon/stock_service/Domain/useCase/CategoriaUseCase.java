package com.emazon.stock_service.Domain.useCase;

import com.emazon.stock_service.Domain.api.ICategoriaService;
import com.emazon.stock_service.Domain.model.Categoria;
import com.emazon.stock_service.Domain.spi.CategoriaPersistencePort;

public class CategoriaUseCase implements ICategoriaService {

    private final CategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(CategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    public void saveCategoria(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        if (categoria.getNombre().length() > 50) {
            throw new IllegalArgumentException("El nombre de la categoría no puede exceder los 50 caracteres");
        }
        if (categoria.getDescripcion() == null || categoria.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía");
        }
        if (categoria.getDescripcion().length() > 90) {
            throw new IllegalArgumentException("La descripción de la categoría no puede exceder los 90 caracteres");
        }
        categoriaPersistencePort.saveCategoria(categoria);
    }
}