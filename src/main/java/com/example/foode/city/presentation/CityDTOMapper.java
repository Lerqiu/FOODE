package com.example.foode.city.presentation;

import com.example.foode.city.service.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityDTOMapper {

    CityDTO toCityDTO(City city);

    City fromCityDTO(CityDTO cityDTO);

}
