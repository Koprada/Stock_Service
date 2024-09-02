package com.emazon.stock_service.Infrastructure.constant;

public class ExceptionConstants {

    //excepciones para categoria
    public static final String CATEGORY_ALREADY_EXISTS = "El nombre de la categoría ya existe";
    public static final String ERROR_SAVING_CATEGORY = "Error al guardar la categoría: ";
    public static final String ERROR_LISTING_CATEGORIES = "Error al listar las categorías: ";

    //excepciones para marca
    public static final String BRAND_ALREADY_EXISTS = "El nombre de la marca ya existe";
    public static final String ERROR_SAVING_BRAND = "Error al guardar la marca: ";
    public static final String ERROR_LISTING_BRANDS = "Error al listar las marcas: ";

    //excepciones para articulo
    public static final String ARTICLE_ALREADY_EXISTS = "El nombre del artículo ya existe";
    public static final String ERROR_SAVING_ARTICLE = "Error al guardar el artículo: ";
    public static final String ERROR_LISTING_ARTICLES = "Error al listar los artículos: ";
    public static final String ARTICLE_CATEGORY_COUNT_INVALID = "Un artículo debe tener entre 1 y 3 categorías.";
    public static final String ARTICLE_CATEGORY_DUPLICATE = "Las categorías no pueden estar repetidas.";
    public static final String ERROR_FETCHING_ARTICLES = "Error al obtener los artículos: ";
}
