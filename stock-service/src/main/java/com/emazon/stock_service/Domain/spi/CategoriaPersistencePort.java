package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Categoria;

public interface CategoriaPersistencePort {

    void saveCategoria(Categoria categoria);
}
