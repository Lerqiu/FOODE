package com.example.foode.city.presentation


import com.example.foode.city.service.CityService
import spock.lang.Specification

class CityControllerSpec extends Specification {
    private CityService cityService
    private CityDTOMapper cityDTOMapper
    private CityController cityController


    void setup() {
        cityService = Mock(CityService)
        cityDTOMapper = Mock(CityDTOMapper.class)
        cityController = new CityController(cityService, cityDTOMapper)
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
