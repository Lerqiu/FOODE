package com.example.foode.offer.service;

import com.example.foode.offer.exception.OfferNotFoundException;
import com.example.foode.offer.persistence.OfferEntity;
import com.example.foode.offer.persistence.OfferFilters;
import com.example.foode.offer.persistence.OfferRepository;
import com.example.foode.offer.persistence.OfferSpecification;
import com.example.foode.offer.presentation.OfferDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public Offer createOffer(Offer offer) {
        OfferEntity offerEntity = offerMapper.toEntity(offer);
        return offerMapper.fromEntity(offerRepository.saveAndFlush(offerEntity));
    }

    public Page<Offer> getOffersFiltered(OfferFilters filters, Pageable pageable) {
        return offerRepository.findAll(new OfferSpecification(filters), pageable)
                .map(offerMapper::fromEntity);
    }

    public Page<Offer> getOffers(Pageable pageable) {
        Page<OfferEntity> offerEntitiesPage = offerRepository.findAll(pageable);
        List<OfferEntity> offerEntities = offerEntitiesPage.getContent();

        List<Offer> offers = offerMapper.fromEntities(offerEntities);

        return new PageImpl<>(offers, pageable, offerEntitiesPage.getTotalElements());
    }

    public Offer getOffer(Long id) {
        return offerMapper.fromEntity(offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id)));
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public Offer updateOffer(Offer newOffer,
                             Long id) {
        return offerRepository.findById(id)
                .map(offer -> cloneAndSaveOffer(newOffer, offerMapper.fromEntity(offer)))
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    private Offer cloneAndSaveOffer(Offer fromOffer,
                                    Offer toOffer) {
        toOffer.buildFrom(fromOffer);
        OfferEntity toOfferEntity = offerMapper.toEntity(toOffer);
        return offerMapper.fromEntity(offerRepository.saveAndFlush(toOfferEntity));
    }

}
