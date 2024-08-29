package com.emazon.stock_service.Domain.Constants;

public class ExceptionConstants {

    // Mensajes de Excepción de Categoría
    public static final String CATEGORY_NAME_EMPTY = "El nombre de la categoría no puede estar vacío";
    public static final String CATEGORY_NAME_TOO_LONG = "El nombre de la categoría no puede exceder los 50 caracteres";
    public static final String CATEGORY_DESCRIPTION_EMPTY = "La descripción de la categoría no puede estar vacía";
    public static final String CATEGORY_DESCRIPTION_TOO_LONG = "La descripción de la categoría no puede exceder los 90 caracteres";
    public static final String CATEGORY_ALREADY_EXISTS = "La categoría ya existe";
    public static final String CATEGORY_NOT_FOUND = "No se encontraron categorías";

    // Mensajes de Excepción de Marca
    public static final String BRAND_NAME_EMPTY = "El nombre de la marca no puede estar vacío";
    public static final String BRAND_NAME_TOO_LONG = "El nombre de la marca no puede exceder los 50 caracteres";
    public static final String BRAND_DESCRIPTION_EMPTY = "La descripción de la marca no puede estar vacía";
    public static final String BRAND_DESCRIPTION_TOO_LONG = "La descripción de la marca no puede exceder los 120 caracteres";
    public static final String BRAND_ALREADY_EXISTS = "La marca ya existe";
    public static final String BRAND_NOT_FOUND = "No se encontraron marcas";

    // Mensajes de Excepción de Artículo
    public static final String ARTICLE_CATEGORY_COUNT_INVALID = "Un artículo debe tener entre 1 y 3 categorías.";
    public static final String ARTICLE_CATEGORY_DUPLICATE = "Las categorías no pueden estar repetidas.";
    public static final String ARTICLE_NAME_EMPTY = "El nombre del artículo no puede estar vacío";
    public static final String ARTICLE_DESCRIPTION_EMPTY = "La descripción del artículo no puede estar vacía";
    public static final String ARTICLE_PRICE_INVALID = "El precio del artículo debe ser mayor que cero";
    public static final String ARTICLE_QUANTITY_INVALID = "La cantidad del artículo debe ser un número positivo";
    public static final String ARTICLE_ALREADY_EXISTS = "El artículo ya existe";
}
