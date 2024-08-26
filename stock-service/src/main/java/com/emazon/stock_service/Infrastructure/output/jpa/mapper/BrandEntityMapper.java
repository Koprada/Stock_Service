package com.emazon.stock_service.Infrastructure.output.jpa.mapper;

import com.emazon.stock_service.Domain.model.Brand;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface BrandEntityMapper {

    BrandEntity brandToBrandEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);
}
