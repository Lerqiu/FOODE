package com.example.foode.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfferRepository extends JpaRepository<Offer, Long>, JpaSpecificationExecutor<OfferProjection> {

    Page<Offer> findAllByUserId(Long userId, Pageable pageable);

    Page<OfferProjection> findAllByCityId(Long cityId, Pageable pageable);

}
