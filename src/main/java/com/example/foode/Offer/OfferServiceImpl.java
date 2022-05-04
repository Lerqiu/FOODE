package com.example.foode.Offer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.saveAndFlush(offer);
    }

    @Override
    public Page<Offer> getOffersByCity(Long cityId, Pageable pageable) {
        return offerRepository.findAllByCityId(cityId, pageable);
    }

    @Override
    public Optional<Offer> getOffer(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepository.saveAndFlush(offer);
    }


}
