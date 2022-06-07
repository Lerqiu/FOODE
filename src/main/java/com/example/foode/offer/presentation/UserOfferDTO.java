package com.example.foode.offer.presentation;

import com.example.foode.user.User;

public record UserOfferDTO(Long id,
                           String login,
                           String contact){

    public static UserOfferDTO from(User user){
        return new UserOfferDTO(user.getId(),
                                    user.getLogin(),
                                    user.getContact());
    }
}
