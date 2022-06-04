package com.example.foode.city

import spock.lang.Specification

class CityServiceSpec extends Specification{
    private CityRepository cityRepository
    private CityService  cityService

    void setup() {
        cityRepository = Mock(CityRepository)
        cityService = new CityService(cityRepository)
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
