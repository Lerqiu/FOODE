package com.example.foode.offer.service;

import com.example.foode.offer.exception.OfferNotFoundException;
import com.example.foode.offer.persistence.OfferFilters;
import com.example.foode.offer.persistence.OfferRepository;
import com.example.foode.offer.persistence.OfferSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Validated
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public Offer createOffer(Offer offer) {
        var offerEntity = offerMapper.toOfferEntity(offer);
        return offerMapper.fromOfferEntity(offerRepository.saveAndFlush(offerEntity));
    }

    public Page<Offer> getOffersFiltered(@Valid OfferFilters filters,
                                         Pageable pageable) {
        return offerRepository.findAll(new OfferSpecification(filters), pageable)
                .map(offerMapper::fromOfferEntity);
    }

    public Page<Offer> getOffers(Pageable pageable) {
        return offerRepository.findAll(pageable)
                .map(offerMapper::fromOfferEntity);
    }

    public Offer getOffer(Long id) {
        return offerMapper.fromOfferEntity(offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id)));
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public Offer updateOffer(Offer newOffer,
                             Long id) {
        return offerRepository.findById(id)
                .map(offer -> cloneAndSaveOffer(newOffer, offerMapper.fromOfferEntity(offer)))
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    private Offer cloneAndSaveOffer(Offer fromOffer,
                                    Offer toOffer) {
        toOffer.buildFrom(fromOffer);
        var toOfferEntity = offerMapper.toOfferEntity(toOffer);
        return offerMapper.fromOfferEntity(offerRepository.saveAndFlush(toOfferEntity));
    }

}
