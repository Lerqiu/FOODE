package com.example.foode.Product;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "products_id_seq", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "products_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "expiration_date")
    private LocalDate expirationDate;

    public Product() {
    }

    public Product(Long id,
                   String name,
                   LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
    }
}
