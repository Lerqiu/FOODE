package com.example.foode.city.service

import com.example.foode.city.persistence.CityRepository
import spock.lang.Specification

class CityServiceSpec extends Specification {
    private CityRepository cityRepository
    private CityService cityService
    private CityMapper cityMapper

    void setup() {
        cityRepository = Mock(CityRepository)
        cityMapper = Mock(CityMapper.class)
        cityService = new CityService(cityRepository, cityMapper)
    }

    def "getAll WHEN called SHOULD return all values from repository"() {
        given:
        cityRepository.findAll() >> []

        when:
        cityService.getAll()

        then:
        1 * cityRepository.findAll() >> []
    }
}
