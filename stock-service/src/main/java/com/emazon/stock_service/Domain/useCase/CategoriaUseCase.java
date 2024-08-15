package com.emazon.stock_service.Domain.useCase;

import com.emazon.stock_service.Domain.model.Categoria;
import com.emazon.stock_service.Domain.spi.CategoriaPersistencePort;

public class CategoriaUseCase implements CategoriaPersistencePort {

    private final CategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(CategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    @Override
    public void saveCategoria(Categoria categoria) {
        categoriaPersistencePort.saveCategoria(categoria);
    }
}
