package com.example.foode.offer;

import com.example.foode.user.User;

public record UserOfferOutput(Long id,
                              String login,
                              String contact){

    public static UserOfferOutput from(User user){
        return new UserOfferOutput(user.getId(),
                                    user.getLogin(),
                                    user.getContact());
    }
}
