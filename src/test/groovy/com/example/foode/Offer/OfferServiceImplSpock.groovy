package com.example.foode.Offer

import com.example.foode.City.City
import com.example.foode.Offer.Exception.OfferNotFoundException
import com.example.foode.Product.Product
import com.example.foode.User.User
import org.springframework.data.domain.*
import spock.lang.Specification

import java.time.LocalDate

class OfferServiceImplSpock extends Specification {

    private OfferService offerService
    private OfferRepository offerRepository

    private Offer offer
    private Offer updatedOffer
    private Offer secondOffer
    private Page<Offer> allOffers

    void setup() {
        offerRepository = Mock(OfferRepository)
        offerService = new OfferService(offerRepository)

        def city = new City(1, "Wroclaw")
        def user = Mock(User)
        def product = Mock(Product)

        offer = new Offer(
                1,
                BigDecimal.ONE,
                LocalDate.now(),
                city,
                "testDesc",
                "testAvailability",
                user,
                product)

        updatedOffer = new Offer(
                1,
                BigDecimal.ZERO,
                LocalDate.now(),
                city,
                "updatedDesc",
                "updatedAvailability",
                user,
                product)

        secondOffer = new Offer(
                2,
                BigDecimal.valueOf(50),
                LocalDate.now(),
                city,
                "secondDesc",
                "secondAvailability",
                user,
                product)

        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))
        def listOfOffers = new ArrayList<Offer>(List.of(offer, secondOffer))

        allOffers = new PageImpl<>(listOfOffers, pageable, 100)
    }

    def "createOffer() WHEN called with offer SHOULD return same offer"() {
        given: "mocked offerRepository"
        offerRepository.saveAndFlush(_ as Offer) >> offer

        when:
        def returnedOffer = offerService.createOffer(offer)

        then:
        offer == returnedOffer
    }

    def "createOffer() WHEN called with offer SHOULD call saveAndFlush() method from offerRepository once"() {
        given: "mocked offerRepository"
        offerRepository.saveAndFlush(_ as Offer) >> offer

        when:
        offerService.createOffer(offer)

        then:
        1 * offerRepository.saveAndFlush(_)
    }

    def "getOffersByCity() WHEN called with city id SHOULD return page of offers with given city id"() {
        given: "city id"
        def cityId = 1

        and: "pageable object"
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        and: "mocked offerRepository"
        offerRepository.findAllByCityId(_ as Long, _ as Pageable) >> allOffers

        when:
        Page<Offer> returnedOffers = offerService.getOffersByCity(cityId, pageable)

        then:
        returnedOffers == allOffers
    }

    def "getOffersByCity() WHEN called with city id SHOULD call findAllByCityId() method from offerRepository once"() {
        given: "city id"
        def cityId = 1

        and: "pageable object"
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        and: "mocked offerRepository"
        offerRepository.findAllByCityId(_ as Long, _ as Pageable) >> allOffers

        when:
        offerService.getOffersByCity(cityId, pageable)

        then:
        1 * offerRepository.findAllByCityId(_, _) >> allOffers
    }

    def "getOffer() WHEN called with id, which is found in offerRepository SHOULD return offer with given id"() {
        given: "searched offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offer)

        when:
        def returnedOffer = offerService.getOffer(offerId)

        then:
        returnedOffer.getId() == offerId
    }

    def "getOffer() WHEN called with id, which is not found in offerRepository SHOULD throw OfferNotFoundException"() {
        given: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.empty()

        when:
        offerService.getOffer(offerId)

        then:
        def offerNotFoundException = thrown(OfferNotFoundException)
        offerNotFoundException.message == "There is no such offer with id: " + offerId

        where:
        _ | offerId
        _ | 8
        _ | 3
        _ | 2
        _ | 15
    }

    def "getOffer() WHEN called with id SHOULD call findById() method from offerRepository once"() {
        given: "searched offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offer)

        when:
        offerService.getOffer(offerId)

        then:
        1 * offerRepository.findById(_) >> Optional.of(offer)
    }

    def "deleteOffer() WHEN called with id SHOULD call deleteById() method from offerRepository once"() {
        given: "id of the offer to delete"
        def offerId = 1

        when:
        offerService.deleteOffer(offerId)

        then:
        1 * offerRepository.deleteById(_)
    }

    def "updateOffer() WHEN called with offer and existing id SHOULD return original updated offer of given id"() {
        given: "offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offer)
        offerRepository.saveAndFlush(_ as Offer) >> offer

        when:
        Offer returnedOffer = offerService.updateOffer(updatedOffer, offerId)

        then:
        returnedOffer == offer
    }

    def "updateOffer() WHEN called with offer and non-existing id SHOULD return given offer with given id"() {
        given: "offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.empty()
        offerRepository.saveAndFlush(_ as Offer) >> updatedOffer

        when:
        Offer returnedOffer = offerService.updateOffer(updatedOffer, offerId)

        then:
        returnedOffer == updatedOffer
    }

    def "updateOffer() WHEN called with offer and id SHOULD call saveAndFlush() method of offerRepository once"() {
        given: "offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offer)

        when:
        offerService.updateOffer(updatedOffer, offerId)
        offerRepository.saveAndFlush(_ as Offer) >> offer

        then:
        1 * offerRepository.saveAndFlush(_) >> offer
    }

}
