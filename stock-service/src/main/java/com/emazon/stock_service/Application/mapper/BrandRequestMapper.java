package com.emazon.stock_service.Application.mapper;

import com.emazon.stock_service.Application.dto.brandDto.BrandDtoRequest;
import com.emazon.stock_service.Application.dto.brandDto.BrandDtoResponse;
import com.emazon.stock_service.Domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BrandRequestMapper {

    Brand toBrand(BrandDtoRequest brandDtoRequest);

    BrandDtoResponse toBrandDtoResponse(Brand brand);
}
