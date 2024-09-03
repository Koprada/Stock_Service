package com.emazon.stock_service.Infrastructure.output.jpa.specification;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

public class ArticleSpecification {

    private ArticleSpecification() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Specification<ArticleEntity> withCustomSorting(String sortBy, String sortOrder) {
        return (Root<ArticleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if ("category.name".equals(sortBy)) {
                Join<ArticleEntity, CategoryEntity> categoryJoin = root.join("categories");
                Path<String> categoryNamePath = categoryJoin.get("name");
                query.orderBy("desc".equalsIgnoreCase(sortOrder)
                        ? criteriaBuilder.desc(categoryNamePath)
                        : criteriaBuilder.asc(categoryNamePath));
            } else if ("brand.name".equals(sortBy)) {
                Join<ArticleEntity, BrandEntity> brandJoin = root.join("brand");
                Path<String> brandNamePath = brandJoin.get("name");
                query.orderBy("desc".equalsIgnoreCase(sortOrder)
                        ? criteriaBuilder.desc(brandNamePath)
                        : criteriaBuilder.asc(brandNamePath));
            } else {
                Path<Object> articlePath = root.get(sortBy);
                query.orderBy("desc".equalsIgnoreCase(sortOrder)
                        ? criteriaBuilder.desc(articlePath)
                        : criteriaBuilder.asc(articlePath));
            }
            return query.getRestriction();
        };
    }

    public static Specification<ArticleEntity> withCategoryNameSorting(String sortOrder) {
        return (root, query, criteriaBuilder) -> {
            Join<ArticleEntity, CategoryEntity> categoryJoin = root.join("categories");
            query.orderBy("asc".equalsIgnoreCase(sortOrder)
                    ? criteriaBuilder.asc(categoryJoin.get("name"))
                    : criteriaBuilder.desc(categoryJoin.get("name")));
            return query.getRestriction();
        };
    }

    public static Specification<ArticleEntity> withBrandNameSorting(String sortOrder) {
        return (root, query, criteriaBuilder) -> {
            Join<ArticleEntity, BrandEntity> brandJoin = root.join("brand");
            query.orderBy("asc".equalsIgnoreCase(sortOrder)
                    ? criteriaBuilder.asc(brandJoin.get("name"))
                    : criteriaBuilder.desc(brandJoin.get("name")));
            return query.getRestriction();
        };
    }

}
