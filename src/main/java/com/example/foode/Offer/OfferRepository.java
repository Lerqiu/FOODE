package com.example.foode.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Page<Offer> findAllByUserId(Long userId, Pageable pageable);

    Page<Offer> findAllByLocation(String location, Pageable pageable);
}
