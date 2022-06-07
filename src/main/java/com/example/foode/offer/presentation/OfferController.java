package com.example.foode.offer.presentation;

import com.example.foode.offer.exception.OfferNotFoundException;
import com.example.foode.offer.persistence.OfferFilters;
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
    private final OfferDTOMapper offerDTOMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDTO createOffer(@RequestBody OfferDTO offerDto) {
        var offer = offerDTOMapper.fromOfferDto(offerDto);
        return offerDTOMapper.toOfferDto(offerService.createOffer(offer));
    }

    @GetMapping()
    public Page<OfferDTO> getOffersFiltered(OfferFilters filters,
                                            Pageable pageable) {
        return offerService.getOffersFiltered(filters, pageable)
                .map(offerDTOMapper::toOfferDto);
    }

    @GetMapping("/{id}")
    public OfferDTO getOffer(@PathVariable Long id) throws OfferNotFoundException {
        return offerDTOMapper.toOfferDto(offerService.getOffer(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDTO updateOffer(@RequestBody OfferDTO offerDto,
                                @PathVariable Long id) {
        var offer = offerDTOMapper.fromOfferDto(offerDto);
        return offerDTOMapper.toOfferDto(offerService.updateOffer(offer, id));
    }
}