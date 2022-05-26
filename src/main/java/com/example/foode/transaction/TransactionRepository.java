package com.example.foode.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAllByBuyerId(Long buyerId, Pageable pageable);

    Page<Transaction> findAllBySellerId(Long sellerId, Pageable pageable);
}
