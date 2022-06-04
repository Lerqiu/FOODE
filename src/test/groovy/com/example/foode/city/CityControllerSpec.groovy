package com.example.foode.city

import spock.lang.Specification

class CityControllerSpec extends Specification{
    private CityService  cityService
    private CityController cityController


    void setup() {
        cityService = Mock(CityService)
        cityController = new CityController(cityService)
    }

    def "getAll WHEN called SHOULD return all values from service"() {
        given:
        cityService.getAll() >> []

        when:
        cityController.getAll()

        then:
        1 * cityService.getAll() >> []
    }
}
