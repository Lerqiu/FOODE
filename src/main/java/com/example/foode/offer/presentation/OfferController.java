package com.example.foode.offer.presentation;

import com.example.foode.offer.exception.OfferNotFoundException;
import com.example.foode.offer.persistence.OfferFilters;
import com.example.foode.offer.presentation.crudmarker.OnOfferCreate;
import com.example.foode.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offers")
@Validated
public class OfferController {

    private final OfferService offerService;
    private final OfferDTOMapper offerDTOMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Validated(OnOfferCreate.class)
    public OfferDTO createOffer(@Valid @RequestBody OfferDTO offerDto) {
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
    public OfferDTO getOffer(@PathVariable @NotNull @Positive Long id) throws OfferNotFoundException {
        return offerDTOMapper.toOfferDto(offerService.getOffer(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOffer(@PathVariable @NotNull @Positive Long id) {
        offerService.deleteOffer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDTO updateOffer(@Valid @RequestBody OfferDTO offerDto,
                                @PathVariable @NotNull @Positive Long id) {
        var offer = offerDTOMapper.fromOfferDto(offerDto);
        return offerDTOMapper.toOfferDto(offerService.updateOffer(offer, id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(error -> {
            var field = "";
            for (Path.Node node : error.getPropertyPath()) {
                field = node.getName();
            }
            errors.put(field, error.getMessage());
        });

        return errors;
    }
}
