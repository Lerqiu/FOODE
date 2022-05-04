package com.example.foode.Offer;

import com.example.foode.Offer.Exception.OfferNotFoundException;
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
    Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Page<Offer> getOffersByCity(@RequestParam Long cityId, Pageable pageable) {
        return offerService.getOffersByCity(cityId, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Offer getOffer(@PathVariable Long id) throws OfferNotFoundException {
        return offerService.getOffer(id)
                .orElseThrow(() -> new OfferNotFoundException(id.toString()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    Offer updateOffer(@RequestBody Offer offer, @PathVariable Long id) {
        return offerService.updateOffer(offer, id);
    }
}