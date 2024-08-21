package com.emazon.stock_service.Application.mapper;


import com.emazon.stock_service.Application.dto.CategoriaDtoRequest;
import com.emazon.stock_service.Domain.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoriaRequestMapper {

    Categoria toCategoria(CategoriaDtoRequest categoriaRequest);
}
