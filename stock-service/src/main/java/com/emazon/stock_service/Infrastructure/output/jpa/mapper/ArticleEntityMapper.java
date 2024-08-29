package com.emazon.stock_service.Infrastructure.output.jpa.mapper;

import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    @Mapping(source = "brandId", target = "brand.id") // Mapea el ID de la marca a la entidad
    @Mapping(source = "categories", target = "categories")
    public abstract ArticleEntity articleToArticleEntity(Article article);

    @Mapping(source = "brand.id", target = "brandId") // Mapea el ID de la marca de la entidad a Article
    @Mapping(source = "categories", target = "categories")
    public abstract Article articleEntityToArticle(ArticleEntity articleEntity);
}
