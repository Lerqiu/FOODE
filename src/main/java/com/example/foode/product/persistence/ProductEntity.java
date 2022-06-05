package com.example.foode.product.persistence;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {

    @Id
    @SequenceGenerator(name = "products_id_seq", sequenceName = "products_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "products_id_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate expirationDate;

    public ProductEntity() {
    }

    public ProductEntity(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public ProductEntity(Long id,
                         String name,
                         LocalDate expirationDate) {
        this(name, expirationDate);
        this.id = id;
    }
}
