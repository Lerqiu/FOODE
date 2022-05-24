package com.example.foode.offer;

import com.example.foode.offer.exception.OfferNotFoundException;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    @GetMapping()
    public Page<OfferProjection> getOffersByCity(@RequestParam Long cityId,
                                                 Pageable pageable) {
        return offerService.getOffersByCity(cityId, pageable);
    }

    @GetMapping("/{id}")
    public Offer getOffer(@PathVariable Long id) throws OfferNotFoundException {
        return offerService.getOffer(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Offer updateOffer(@RequestBody Offer offer,
                             @PathVariable Long id) {
        return offerService.updateOffer(offer, id);
    }
}