package com.example.foode.offer.presentation;

import com.example.foode.offer.service.Offer;
import com.example.foode.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = UserRepository.class)
public abstract class OfferDTOMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "userOutput",
            expression = "java(new UserOfferOutput(" +
                    "offer.getUser().getId()," +
                    "offer.getUser().getLogin()," +
                    "offer.getUser().getContact()))")
    public abstract OfferDTO toOfferDto(Offer offer);

    @Mapping(target = "user",
            expression = "java(userRepository.findById(offerDto.getUserOutput().id()).orElseGet(User::new))")
    public abstract Offer fromOfferDto(OfferDTO offerDto);
}
