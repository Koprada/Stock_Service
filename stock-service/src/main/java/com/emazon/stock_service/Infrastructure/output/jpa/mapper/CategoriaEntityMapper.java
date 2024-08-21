package com.emazon.stock_service.Infrastructure.output.jpa.mapper;


import com.emazon.stock_service.Domain.model.Categoria;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface CategoriaEntityMapper {

    CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);

    Categoria toCategoria(CategoriaEntity categoriaEntity);

}
