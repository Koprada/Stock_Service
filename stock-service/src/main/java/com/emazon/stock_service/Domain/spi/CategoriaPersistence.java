package com.emazon.stock_service.Domain.spi;

import com.emazon.stock_service.Domain.model.Categoria;

public interface CategoriaPersistence {

    void saveCategoria(Categoria categoria);
}
