package com.example.foode.offer.service;

import com.example.foode.offer.persistence.OfferEntity;
import com.example.foode.product.service.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel="spring", uses = ProductMapper.class)
public interface OfferMapper {

    @Mapping(target = "productEntity", source = "product")
    OfferEntity toOfferEntity(Offer offer);

    @Mapping(target = "product", source = "productEntity")
    Offer fromOfferEntity(OfferEntity offerEntity);

    List<OfferEntity> toEntities(Collection<Offer> offerEntities);

    List<Offer> fromOfferEntities(Collection<OfferEntity> offerEntities);
}
