package com.example.foode.city.presentation;

import com.example.foode.city.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;
    private final CityDTOMapper cityDTOMapper;

    @GetMapping()
    public List<CityDTO> getAll() {
        return cityDTOMapper.fromCities(cityService.getAll());
    }

}
