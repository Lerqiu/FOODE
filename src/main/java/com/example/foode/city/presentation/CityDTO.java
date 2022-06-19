package com.example.foode.city.presentation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CityDTO {

    @NotNull
    @Positive(message = "Id must be positive")
    private Long id;

    @NotBlank(message = "City name cannot be blank")
    private String name;

}
