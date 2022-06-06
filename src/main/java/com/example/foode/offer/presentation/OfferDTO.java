package com.example.foode.offer.presentation;

import com.example.foode.city.City;
import com.example.foode.product.presentation.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OfferDTO {
    private Long id;

    private BigDecimal price;

    private LocalDate date;

    private City city;

    private String description;

    private String availability;

    private ProductDTO product;

    private UserOfferOutput userOutput;

    public OfferDTO() {

    }

    public OfferDTO(Long id,
                    BigDecimal price,
                    LocalDate date,
                    City city,
                    String description,
                    String availability,
                    ProductDTO product,
                    UserOfferOutput userOutput) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.city = city;
        this.description = description;
        this.availability = availability;
        this.product = product;
        this.userOutput = userOutput;
    }
}
