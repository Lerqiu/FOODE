package com.example.foode.city.persistence;

import com.example.foode.city.persistence.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    List<CityEntity> findAllByNameContainingIgnoreCase(String name);
}
