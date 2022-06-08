package com.example.foode.offer.persistence;

import lombok.Data;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class OfferFilters {

    private final String name;
    private final Long cityId;
    @PositiveOrZero
    private final BigDecimal priceFrom;
    @PositiveOrZero
    private final BigDecimal priceTo;
}
