package com.example.foode.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAll(){
        return cityRepository.findAll();
    }
}
