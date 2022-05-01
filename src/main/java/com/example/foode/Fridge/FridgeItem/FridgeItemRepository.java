package com.example.foode.Fridge.FridgeItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FridgeItemRepository extends JpaRepository<FridgeItem, Long> {

    Optional<FridgeItem> findByFridgeId(Long fridgeId);
}
