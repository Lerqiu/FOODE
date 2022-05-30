package com.example.foode.offer.presentation;

import com.example.foode.offer.exception.OfferNotFoundException;
import com.example.foode.offer.persistence.OfferFilters;
import com.example.foode.offer.service.Offer;
import com.example.foode.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;
    private final OfferProjectionMapper offerProjectionMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto createOffer(@RequestBody OfferDto offerDto) {
        Offer offer = offerProjectionMapper.fromOfferDto(offerDto);
        return offerProjectionMapper.toOfferDto(offerService.createOffer(offer));
    }

    @GetMapping()
    public Page<OfferDto> getOffersFiltered(OfferFilters filters,
                                            Pageable pageable) {
        return offerService.getOffersFiltered(filters, pageable);
    }

    @GetMapping("/{id}")
    public OfferDto getOffer(@PathVariable Long id) throws OfferNotFoundException {
        return offerProjectionMapper.toOfferDto(offerService.getOffer(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto updateOffer(@RequestBody OfferDto offerDto,
                                @PathVariable Long id) {
        Offer offer = offerProjectionMapper.fromOfferDto(offerDto);
        return offerProjectionMapper.toOfferDto(offerService.updateOffer(offer, id));
    }
}