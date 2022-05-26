package com.example.foode.offer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(Long id) {
        super("There is no such offer with id: " + id);
    }
}
