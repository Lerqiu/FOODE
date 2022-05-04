package com.example.foode.Offer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        var newOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(newOffer, HttpStatus.CREATED);
    }

    @GetMapping()
    Page<Offer> getOffersByCity(@RequestParam Long cityId, Pageable pageable) {
        return offerService.getOffersByCity(cityId, pageable);
    }

    @GetMapping("/{id}")
    ResponseEntity<Offer> getOffer(@PathVariable Long id) {
        return offerService.getOffer(id)
                .map(offer -> ResponseEntity.ok().body(offer))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }

    @PutMapping
    ResponseEntity<Offer> updateOffer(@RequestBody Offer offer) {
        var updatedOffer = offerService.updateOffer(offer);

        return new ResponseEntity<>(updatedOffer, HttpStatus.CREATED);
    }
}