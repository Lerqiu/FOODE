package com.example.foode.product.presentation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private LocalDate expirationDate;

    public ProductDTO() {
    }

    public ProductDTO(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public ProductDTO(Long id,
                   String name,
                   LocalDate expirationDate) {
        this(name, expirationDate);
        this.id = id;
    }

}
