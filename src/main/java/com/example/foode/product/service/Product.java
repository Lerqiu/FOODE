package com.example.foode.product.service;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Product {

    private Long id;

    private String name;

    private LocalDate expirationDate;

    public Product() {
    }

    public Product(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public Product(Long id,
                   String name,
                   LocalDate expirationDate) {
        this(name, expirationDate);
        this.id = id;
    }

}
