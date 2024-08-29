package com.emazon.stock_service.Application.dto.brandDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDtoRequest {

    private String name;
    private String description;

}