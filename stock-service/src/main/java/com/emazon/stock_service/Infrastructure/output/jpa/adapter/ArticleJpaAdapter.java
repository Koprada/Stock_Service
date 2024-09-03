package com.emazon.stock_service.Infrastructure.output.jpa.adapter;

import com.emazon.stock_service.Domain.exception.InvalidArticleException;
import com.emazon.stock_service.Domain.model.Article;
import com.emazon.stock_service.Domain.model.Category;
import com.emazon.stock_service.Domain.model.Pagination;
import com.emazon.stock_service.Domain.spi.ArticlePersistencePort;
import com.emazon.stock_service.Infrastructure.constant.ExceptionConstants;
import com.emazon.stock_service.Infrastructure.exception.DatabaseException;
import com.emazon.stock_service.Infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_service.Infrastructure.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock_service.Infrastructure.output.jpa.repository.IArticleRepository;
import com.emazon.stock_service.Infrastructure.output.jpa.specification.ArticleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements ArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        try {
            if (articleRepository.existsByName(article.getName())) {
                throw new IllegalArgumentException(ExceptionConstants.ARTICLE_ALREADY_EXISTS);
            }
            if (article.getBrandId() == null) {
                throw new IllegalArgumentException(ExceptionConstants.ERROR_SAVING_ARTICLE);
            }
            validateCategories(article.getCategories());
            articleRepository.save(articleEntityMapper.articleToArticleEntity(article));

        } catch (IllegalArgumentException e) {
            throw new InvalidArticleException(ExceptionConstants.ERROR_SAVING_ARTICLE + e.getMessage());
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_SAVING_ARTICLE + e.getMessage(), e);
        }
    }

    private void validateCategories(List<Category> categories) {
        if (categories == null || categories.isEmpty() || categories.size() > 3) {
            throw new IllegalArgumentException(ExceptionConstants.ARTICLE_CATEGORY_COUNT_INVALID);
        }
        if (categories.stream().distinct().count() != categories.size()) {
            throw new IllegalArgumentException(ExceptionConstants.ARTICLE_CATEGORY_DUPLICATE);
        }
    }

    @Override
    public boolean existsByName(String name) {
        return articleRepository.existsByName(name);
    }

    @Override
    public Pagination<Article> listArticles(Pageable pageable) {
        try {
            Page<ArticleEntity> articleEntityPage = articleRepository.findAll(pageable);
            return convertToPagination(articleEntityPage);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_LISTING_ARTICLES + e.getMessage(), e);
        }
    }

    @Override
    public Pagination<Article> listArticlesWithCustomSorting(String sortBy, String sortOrder, Pageable pageable) {
        return getArticlePagination(sortBy, sortOrder, pageable);
    }

    private Pagination<Article> getArticlePagination(String sortBy, String sortOrder, Pageable pageable) {
        try {
            Specification<ArticleEntity> specification = ArticleSpecification.withCustomSorting(sortBy, sortOrder);
            Page<ArticleEntity> articlesPage = articleRepository.findAll(specification, pageable);
            return convertToPagination(articlesPage);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_FETCHING_ARTICLES + e.getMessage(), e);
        }
    }

    @Override
    public Pagination<Article> listArticlesSortedByCategoryName(String sortOrder, Pageable pageable) {
        try {
            Specification<ArticleEntity> specification = ArticleSpecification.withCategoryNameSorting(sortOrder);
            Page<ArticleEntity> articleEntityPage = articleRepository.findAll(specification, pageable);

            return convertToPagination(articleEntityPage);
        } catch (Exception e) {
            throw new DatabaseException("Error al obtener los artículos: " + e.getMessage(), e);
        }
    }



    @Override
    public Pagination<Article> listArticlesByCategoryName(String categoryName, Pageable pageable) {
        try {
            Specification<ArticleEntity> specification = ArticleSpecification.withCategoryNameSorting(categoryName);
            Page<ArticleEntity> articleEntityPage = articleRepository.findAll(specification, pageable);
            return convertToPagination(articleEntityPage);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_FETCHING_ARTICLES + e.getMessage(), e);
        }
    }

    @Override
    public Pagination<Article> listArticlesSortedByBrandName(String sortOrder, Pageable pageable) {
        try {
            Specification<ArticleEntity> specification = ArticleSpecification.withBrandNameSorting(sortOrder);
            Page<ArticleEntity> articleEntityPage = articleRepository.findAll(specification, pageable);
            return convertToPagination(articleEntityPage);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionConstants.ERROR_FETCHING_ARTICLES + e.getMessage(), e);
        }
    }

    @Override
    public Pagination<Article> listArticlesSortedByArticleField(String sortBy, String sortOrder, Pageable pageable) {
        return getArticlePagination(sortBy, sortOrder, pageable);
    }

    private Pagination<Article> convertToPagination(Page<ArticleEntity> articleEntityPage) {
        if (articleEntityPage == null) {
            return new Pagination<>(
                    Collections.emptyList(),
                    0, // página
                    0, // tamaño
                    0, // total de elementos
                    0, // total de páginas
                    true // es la última página
            );
        }

        List<Article> articles = articleEntityPage.getContent().stream()
                .map(articleEntityMapper::articleEntityToArticle)
                .toList();
        return new Pagination<>(
                articles,
                articleEntityPage.getNumber(),
                articleEntityPage.getSize(),
                articleEntityPage.getTotalElements(),
                articleEntityPage.getTotalPages(),
                articleEntityPage.isLast()
        );
    }

}
