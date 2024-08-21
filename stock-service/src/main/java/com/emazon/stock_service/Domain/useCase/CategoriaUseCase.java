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
        categoriaPersistencePort.saveCategoria(categoria);
    }
}
