package com.example.foode.Offer;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    Offer createOffer(Offer offer);

    List<Offer> getOffersByCity(Long cityId, Pageable pageable);

    Optional<Offer> getOffer(Long id);

    void deleteOffer(Long id);
}
