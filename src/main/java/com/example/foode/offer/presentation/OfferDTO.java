package com.example.foode.offer.presentation;

import com.example.foode.city.presentation.CityDTO;
import com.example.foode.product.presentation.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OfferDTO {
    private Long id;

    private BigDecimal price;

    private LocalDate date;

    private CityDTO city;

    private String description;

    private String availability;

    private ProductDTO product;

    private UserOfferOutput userOutput;
}
