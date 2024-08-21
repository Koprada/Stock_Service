package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.model.Categoria;
import com.emazon.stock_service.Domain.spi.CategoriaPersistencePort;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.CategoriaEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaJpaAdapter implements CategoriaPersistencePort {

    private final ICategoriaRepository categoriaRepository;
    private final CategoriaEntityMapper categoriaEntityMapper;

    @Override
    public void saveCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new IllegalArgumentException("El nombre de la categoría ya existe");
        }
        categoriaRepository.save(categoriaEntityMapper.categoriaToCategoriaEntity(categoria));
    }
}