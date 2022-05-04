package com.example.foode.Offer.Exception;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(String errorMessage) {
        super("There is no such offer with id: " + errorMessage);
    }
}
