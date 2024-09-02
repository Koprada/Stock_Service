package com.emazon.stock_service.Infrastructure.output.jpa.mapper;

import com.emazon.stock_service.Application.dto.ArticleDto.ArticleDtoResponse;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "categories", target = "categories")
    ArticleEntity articleToArticleEntity(Article article);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "categories", target = "categories")
    Article articleEntityToArticle(ArticleEntity articleEntity);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "categories", target = "categories")
    ArticleDtoResponse articleEntityToArticleDtoResponse(ArticleEntity articleEntity);
}
