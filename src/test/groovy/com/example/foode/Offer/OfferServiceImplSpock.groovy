package com.example.foode.Offer

import com.example.foode.City.City
import com.example.foode.Offer.Exception.OfferNotFoundException
import com.example.foode.Product.Product
import com.example.foode.User.User
import spock.lang.Specification

import java.time.LocalDate

class OfferServiceImplSpock extends Specification {

    private OfferService offerService
    private OfferRepository offerRepository

    private Offer offer
    private Offer updatedOffer

    void setup() {
        offerRepository = Mock(OfferRepository)
        offerService = new OfferServiceImpl(offerRepository)

        def city = Mock(City)
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

    def "getOffersByCity"() {
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
