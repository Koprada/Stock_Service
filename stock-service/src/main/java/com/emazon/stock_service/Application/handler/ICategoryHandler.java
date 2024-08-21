package com.emazon.stock_service.Application.handler;

import com.emazon.stock_service.Application.dto.CategoriaDtoRequest;

public interface ICategoryHandler {
    void saveCategory(CategoriaDtoRequest categoriaDtoRequest);
}
