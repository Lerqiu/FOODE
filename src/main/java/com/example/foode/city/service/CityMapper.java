package com.example.foode.city.service;

import com.example.foode.city.persistence.CityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityEntity toCityEntity(City city);

    City fromCityEntity(CityEntity cityEntity);

    List<City> fromCityEntities(List<CityEntity> cities);

}
