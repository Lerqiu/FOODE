package com.example.foode.product.presentation;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ProductDTO {
    @Positive(message = "Id must be positive")
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 255, message = "Product name must be of max size 255")
    private String name;

    @FutureOrPresent(message = "Expiration date should be from future or present")
    private LocalDate expirationDate;

    public ProductDTO() {
    }

}
