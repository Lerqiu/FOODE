package com.example.foode.Product.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String errorMessage) {
        super("There is no such product with id: " + errorMessage);
    }
}
