package com.example.foode.offer.persistence;

import com.example.foode.offer.presentation.OfferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfferRepository extends JpaRepository<OfferEntity, Long>, JpaSpecificationExecutor<OfferDto> {

    Page<OfferEntity> findAllByUserId(Long userId, Pageable pageable);
}
