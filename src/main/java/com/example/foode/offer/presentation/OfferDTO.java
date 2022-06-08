package com.example.foode.offer.presentation;

import com.example.foode.city.presentation.CityDTO;
import com.example.foode.product.presentation.ProductDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OfferDTO {
    @Positive(message = "Id must be positive")
    private Long id;

    @NotNull
    @PositiveOrZero(message = "Price must be non-negative")
    private BigDecimal price;

    @NotNull
    private LocalDate date;

    @NotNull
    @Valid
    private CityDTO city;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 4000, message = "Description must be of max size 4000")
    private String description;

    @NotBlank(message = "Availability cannot be blank")
    @Size(max = 255, message = "Availability must be of max size 255")
    private String availability;

    @NotNull
    @Valid
    private ProductDTO product;

    @NotNull
    @Valid
    private UserOfferDTO userOutput;
}
