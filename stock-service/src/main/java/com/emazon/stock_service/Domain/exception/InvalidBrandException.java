package com.emazon.stock_service.Domain.exception;

public class InvalidBrandException extends RuntimeException {
    public InvalidBrandException(String message) {
        super(message);
    }
}
