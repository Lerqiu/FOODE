package com.example.foode.offer.service;

import com.example.foode.offer.persistence.OfferEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel="spring")
public interface OfferMapper {

    OfferEntity toEntity(Offer offer);

    Offer fromEntity(OfferEntity offerEntity);

    List<OfferEntity> toEntities(Collection<Offer> offerEntities);

    List<Offer> fromEntities(Collection<OfferEntity> offerEntities);
}