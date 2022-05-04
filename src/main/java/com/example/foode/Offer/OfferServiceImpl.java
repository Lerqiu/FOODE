package com.example.foode.Offer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.saveAndFlush(offer);
    }

    @Override
    public List<Offer> getOffersByCity(Long cityId, Pageable pageable) {
        return offerRepository.findAllByCityId(cityId, pageable)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Offer> getOffer(Long id) {
        return offerRepository.findById(id);
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}
