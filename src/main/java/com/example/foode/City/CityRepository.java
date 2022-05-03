package com.example.foode.City;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByNameContainingIgnoreCase(String name);
}
