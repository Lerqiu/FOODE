package com.example.foode.offer.presentation;

import com.example.foode.offer.service.Offer;
import com.example.foode.user.User;
import com.example.foode.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OfferProjectionMapper {

    private final UserRepository userRepository;

    public OfferDto toOfferDto(Offer offer) {
        return new OfferDto(
                offer.getId(),
                offer.getPrice(),
                offer.getDate(),
                offer.getCity(),
                offer.getDescription(),
                offer.getAvailability(),
                offer.getProduct(),
                new UserOfferOutput(
                        offer.getUser().getId(),
                        offer.getUser().getLogin(),
                        offer.getUser().getContact()
                ));
    }

    public Offer fromOfferDto(OfferDto offerDto) {
        User user = userRepository.findById(offerDto.getUserOutput().id()).orElseGet(User::new);

        Offer offer = fromOfferDtoWithoutUser(offerDto);
        offer.setUser(user);

        return offer;
    }

    private Offer fromOfferDtoWithoutUser(OfferDto offerDto) {
        return new Offer(
                offerDto.getId(),
                offerDto.getPrice(),
                offerDto.getDate(),
                offerDto.getCity(),
                offerDto.getDescription(),
                offerDto.getAvailability(),
                offerDto.getProduct());
    }

}
