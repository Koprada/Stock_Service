package com.emazon.stock_service.Infrastructure.output.jpa.specification;

import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.CategoryEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Join;

public class ArticleSpecification {

    private ArticleSpecification() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Specification<ArticleEntity> withCustomSorting(String sortBy, String sortOrder) {
        return (Root<ArticleEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if ("category.name".equals(sortBy)) {
                Join<ArticleEntity, CategoryEntity> categoriesJoin = root.join("categories");
                Path<String> categoryNamePath = categoriesJoin.get("name");
                assert query != null;
                query.orderBy("desc".equalsIgnoreCase(sortOrder)
                        ? criteriaBuilder.desc(categoryNamePath)
                        : criteriaBuilder.asc(categoryNamePath));
            } else if ("brand.name".equals(sortBy)) {
                Join<ArticleEntity, BrandEntity> brandJoin = root.join("brand");
                Path<String> brandNamePath = brandJoin.get("name");
                assert query != null;
                query.orderBy("desc".equalsIgnoreCase(sortOrder)
                        ? criteriaBuilder.desc(brandNamePath)
                        : criteriaBuilder.asc(brandNamePath));
            } else {
                Path<Object> articlePath = root.get(sortBy);
                assert query != null;
                query.orderBy("desc".equalsIgnoreCase(sortOrder)
                        ? criteriaBuilder.desc(articlePath)
                        : criteriaBuilder.asc(articlePath));
            }

            return query.getRestriction();
        };
    }
}
