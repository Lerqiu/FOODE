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


    @Column(nullable = false)
    private LocalDate expirationDate;

}
