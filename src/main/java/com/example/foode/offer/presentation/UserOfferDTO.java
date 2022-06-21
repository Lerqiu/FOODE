package com.example.foode.offer.presentation;

import com.example.foode.user.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public final class UserOfferDTO {

    @NotNull
    @Positive(message = "Id must be positive")
    private Long id;

    private String login;

    private String contact;

    public UserOfferDTO(Long id,
                        String login,
                        String contact) {
        this.id = id;
        this.login = login;
        this.contact = contact;
    }

    public static UserOfferDTO from(User user) {
        return new UserOfferDTO(user.getId(),
                user.getLogin(),
                user.getContact());
    }

}
