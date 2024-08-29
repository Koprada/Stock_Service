package com.emazon.stock_service.Domain.exception;

public class InvalidArticleException extends RuntimeException {
    public InvalidArticleException(String message) {
        super(message);
    }
}
