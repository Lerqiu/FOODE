package com.example.foode.offer.persistence;

import com.example.foode.city.City;
import com.example.foode.product.persistence.ProductEntity;
import com.example.foode.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offer")
@Data
public class OfferEntity {

    @Id
    @SequenceGenerator(name = "offers_id_seq", sequenceName = "offers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "offers_id_seq")
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @Column(length = 4000, nullable = false)
    private String description;

    @Column(length = 200, nullable = false)
    private String availability;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productEntity;

    public OfferEntity() {
    }

    public OfferEntity(
            Long id,
            BigDecimal price,
            LocalDate date,
            City city,
            String description,
            String availability,
            User user,
            ProductEntity productEntity) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.city = city;
        this.description = description;
        this.availability = availability;
        this.user = user;
        this.productEntity = productEntity;
    }

    public OfferEntity(BigDecimal price,
                       City city,
                       LocalDate date,
                       String description,
                       String availability,
                       ProductEntity productEntity,
                       User user) {
        this.price = price;
        this.city = city;
        this.date = date;
        this.description = description;
        this.availability = availability;
        this.productEntity = productEntity;
        this.user = user;
    }

}
