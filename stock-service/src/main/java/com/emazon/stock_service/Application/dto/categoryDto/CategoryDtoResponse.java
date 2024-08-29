package com.emazon.stock_service.Application.dto.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoResponse {

    private Long id;
    private String name;
    private String description;

}
