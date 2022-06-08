package com.example.foode.offer.presentation;

import com.example.foode.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UserOfferDTO(@NotNull @Positive(message = "Id must be positive") Long id,
                           @NotBlank(message = "Login cannot be blank") String login,
                           @NotBlank(message = "Contact cannot be blank") String contact){

    public static UserOfferDTO from(User user){
        return new UserOfferDTO(user.getId(),
                                    user.getLogin(),
                                    user.getContact());
    }
}
