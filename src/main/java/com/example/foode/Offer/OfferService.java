package com.example.foode.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OfferService {

    Offer createOffer(Offer offer);

    Page<Offer> getOffersByCity(Long cityId,
                                Pageable pageable);

    Offer getOffer(Long id);

    void deleteOffer(Long id);

    Offer updateOffer(Offer newOffer, Long id);
}
