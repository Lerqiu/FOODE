package com.example.foode.city;

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

    @GetMapping()
    public List<City> getAll() {
        return cityService.getAll();
    }

}
