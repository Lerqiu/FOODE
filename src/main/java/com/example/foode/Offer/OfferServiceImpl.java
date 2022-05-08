package com.example.foode.Offer;

import com.example.foode.Offer.Exception.OfferNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.saveAndFlush(offer);
    }

    @Override
    public Page<Offer> getOffersByCity(Long cityId,
                                       Pageable pageable) {
        return offerRepository.findAllByCityId(cityId, pageable);
    }

    @Override
    public Offer getOffer(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public Offer updateOffer(Offer newOffer,
                             Long id) {
        return offerRepository.findById(id)
                .map(offer -> cloneAndSaveOffer(newOffer, offer))
                .orElseGet(() -> saveNewOffer(newOffer, id));
    }

    private Offer cloneAndSaveOffer(Offer fromOffer,
                                    Offer toOffer) {
        toOffer.setFrom(fromOffer);
        return offerRepository.saveAndFlush(toOffer);
    }

    private Offer saveNewOffer(Offer offer,
                               Long id) {
        offer.setId(id);
        return offerRepository.saveAndFlush(offer);
    }

}
