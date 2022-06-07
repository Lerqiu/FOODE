package com.example.foode.city.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    List<CityEntity> findAllByNameContainingIgnoreCase(String name);
}
