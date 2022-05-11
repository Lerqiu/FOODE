package com.example.foode.Offer;

import com.example.foode.User.User;

public record UserOfferOutput(Long id,
                              String login,
                              String contact){

    public static UserOfferOutput from(User user){
        return new UserOfferOutput(user.getId(),
                                    user.getLogin(),
                                    user.getContact());
    }
}
