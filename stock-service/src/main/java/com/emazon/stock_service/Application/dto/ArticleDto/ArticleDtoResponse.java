package com.emazon.stock_service.Application.dto.ArticleDto;

import com.emazon.stock_service.Domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDtoResponse {

    private Long id;
    private String name;
    private String description;
    private int amount;
    private double price;
    private Long brandId;
    private List<Category> categories;
}
