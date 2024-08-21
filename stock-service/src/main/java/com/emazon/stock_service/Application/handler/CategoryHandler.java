package com.emazon.stock_service.Application.handler;
import com.emazon.stock_service.Application.dto.CategoriaDtoRequest;
import com.emazon.stock_service.Application.mapper.CategoriaRequestMapper;
import com.emazon.stock_service.Domain.api.ICategoriaService;
import com.emazon.stock_service.Domain.model.Categoria;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryHandler implements ICategoryHandler {

    private final ICategoriaService categoriaService;
    private final CategoriaRequestMapper categoriaRequestMapper;

    @Override
    public void saveCategory(CategoriaDtoRequest categoriaDtoRequest) {
        Categoria categoria = categoriaRequestMapper.toCategoria(categoriaDtoRequest);
        categoriaService.saveCategoria(categoria);
    }
}
