package com.example.foode.city.presentation;

import com.example.foode.city.service.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityDTOMapper {

    CityDTO toCityDTO(City city);

    City fromCityDTO(CityDTO cityDTO);

    List<CityDTO> fromCities(List<City> cities);

}
