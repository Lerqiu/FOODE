package com.example.foode.offer.service;

import com.example.foode.city.service.City;
import com.example.foode.product.service.Product;
import com.example.foode.user.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Offer {

    private Long id;

    private BigDecimal price;

    private LocalDate date;

    private City city;

    private String description;

    private String availability;

    private User user;

    private Product product;

    public Offer(Long id,
                 BigDecimal price,
                 LocalDate date,
                 City city,
                 String description,
                 String availability,
                 User user,
                 Product product) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.city = city;
        this.description = description;
        this.availability = availability;
        this.user = user;
        this.product = product;
    }

    public void buildFrom(Offer offer) {
        this.setAvailability(offer.getAvailability());
        this.setCity(offer.getCity());
        this.setDate(offer.getDate());
        this.setDescription(offer.getDescription());
        this.setPrice(offer.getPrice());
        this.setProduct(offer.getProduct());
        this.setCity(offer.getCity());
    }

}
