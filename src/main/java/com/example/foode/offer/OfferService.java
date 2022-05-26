package com.example.foode.offer;

import com.example.foode.offer.exception.OfferNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public Offer createOffer(Offer offer) {
        return offerRepository.saveAndFlush(offer);
    }

    public Page<OfferProjection> getOffersFiltered(OfferFilters filters, Pageable pageable) {
        return offerRepository.findAll(new OfferSpecification(filters), pageable);
    }

    public Page<OfferProjection> getOffersByCity(Long cityId,
                                                 Pageable pageable) {
        return offerRepository.findAllByCityId(cityId, pageable);
    }

    public Page<Offer> getOffers(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    public Offer getOffer(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    public Offer updateOffer(Offer newOffer,
                             Long id) {
        return offerRepository.findById(id)
                .map(offer -> cloneAndSaveOffer(newOffer, offer))
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    private Offer cloneAndSaveOffer(Offer fromOffer,
                                    Offer toOffer) {
        toOffer.setFrom(fromOffer);
        return offerRepository.saveAndFlush(toOffer);
    }

}
