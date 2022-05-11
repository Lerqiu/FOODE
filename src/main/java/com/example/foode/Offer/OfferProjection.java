package com.example.foode.Offer;

import com.example.foode.City.City;
import com.example.foode.Product.Product;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OfferProjection {
    Long getId();

    BigDecimal getPrice();

    LocalDate getDate();

    City getCity();

    String getDescription();

    String getAvailability();

    @Value("#{target.user != null ? target.userOutput : ''}")
    UserOfferOutput getUserOutput();

    Product getProduct();

}
