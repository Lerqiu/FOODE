package com.example.foode.offer.persistence;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfferFilters {

    private final String name;
    private final Long cityId;
    private final BigDecimal priceFrom;
    private final BigDecimal priceTo;
}
