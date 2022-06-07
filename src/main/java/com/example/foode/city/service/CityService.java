package com.example.foode.city.service;

import com.example.foode.city.persistence.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<City> getAll() {
        return cityMapper.fromCityEntities(cityRepository.findAll());
    }
}
