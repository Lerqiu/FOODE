package com.example.foode.offer

import com.example.foode.city.City
import com.example.foode.offer.exception.OfferNotFoundException
import com.example.foode.offer.persistence.OfferEntity
import com.example.foode.offer.persistence.OfferFilters
import com.example.foode.offer.persistence.OfferRepository
import com.example.foode.offer.presentation.OfferDTO
import com.example.foode.offer.service.Offer
import com.example.foode.offer.service.OfferMapper
import com.example.foode.offer.service.OfferService
import com.example.foode.product.Product
import com.example.foode.user.User
import org.springframework.data.domain.*
import spock.lang.Specification

import java.time.LocalDate

class OfferServiceSpec extends Specification {

    private OfferService offerService

    private OfferRepository offerRepository

    private OfferMapper offerMapper

    private OfferEntity offerEntity
    private OfferEntity updatedOfferEntity
    private OfferEntity secondOfferEntity

    private Offer offer
    private Offer updatedOffer
    private Offer secondOffer

    private Page<OfferEntity> allOffers

    void setup() {
        offerRepository = Mock(OfferRepository)
        offerMapper = Mock(OfferMapper)
        offerService = new OfferService(offerRepository, offerMapper)

        def city = new City(1, "Wroclaw")
        def user = Mock(User)
        def product = Mock(Product)

        offerEntity = new OfferEntity(
                1,
                BigDecimal.ONE,
                LocalDate.now(),
                city,
                "testDesc",
                "testAvailability",
                user,
                product)

        updatedOfferEntity = new OfferEntity(
                1,
                BigDecimal.ZERO,
                LocalDate.now(),
                city,
                "updatedDesc",
                "updatedAvailability",
                user,
                product)

        secondOfferEntity = new OfferEntity(
                2,
                BigDecimal.valueOf(50),
                LocalDate.now(),
                city,
                "secondDesc",
                "secondAvailability",
                user,
                product)

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
        def listOfOffers = new ArrayList<OfferEntity>(List.of(offerEntity, secondOfferEntity))

        allOffers = new PageImpl<>(listOfOffers, pageable, 100)
    }

    def "createOffer() WHEN called with offer SHOULD return same offer"() {
        given: "mocked offerRepository"
        offerRepository.saveAndFlush(_ as OfferEntity) >> offerEntity

        and: "mocked offerMapper"
        offerMapper.toEntity(_ as Offer) >> offerEntity
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "createOffer() returns newly created offer"
        def returnedOffer = offerService.createOffer(offer)

        then: "newly created offer is same as we given as parameter"
        offer.getDate() == returnedOffer.getDate()
        offer.getPrice() == returnedOffer.getPrice()
        offer.getDescription() == returnedOffer.getDescription()
        offer.getAvailability() == returnedOffer.getAvailability()
        offer.getId() == returnedOffer.getId()
    }

    def "createOffer() WHEN called with offer SHOULD call saveAndFlush() method from offerRepository once"() {
        given: "mocked offerRepository"
        offerRepository.saveAndFlush(_ as OfferEntity) >> offerEntity

        and: "mocked offerMapper"
        offerMapper.toEntity(_ as Offer) >> offerEntity
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "we run createOffer()"
        offerService.createOffer(offer)

        then: "saveAndFlush() is called once"
        1 * offerRepository.saveAndFlush(_) >> offerEntity
        1 * offerMapper.toEntity(_ as Offer) >> offerEntity
        1 * offerMapper.fromEntity(_ as OfferEntity) >> offer
    }

    def "getOffersFiltered() WHEN called with filters SHOULD return exactly what repository returns"() {
        given: "filters"
        def filters = new OfferFilters("name", 1L, BigDecimal.ONE, BigDecimal.TEN);

        and: "pageable object"
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        and: "mocked offerRepository"
        offerRepository.findAll(
                _ as org.springframework.data.jpa.domain.Specification<OfferEntity>,
                _ as Pageable)
                >> allOffers

        when: "getOffersFiltered() returns Page of offers"
        Page<Offer> returnedOffers = offerService.getOffersFiltered(filters, pageable)

        then: "results from service repository are identical"
        returnedOffers == allOffers
    }

    def "getOffersFiltered() WHEN called with filters SHOULD call findAll() method from offerRepository once"() {
        given: "filters"
        def filters = new OfferFilters("name", 1L, BigDecimal.ONE, BigDecimal.TEN);

        and: "pageable object"
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("name")))

        and: "mocked offerRepository"
        offerRepository.findAll(
                _ as org.springframework.data.jpa.domain.Specification<OfferEntity>,
                _ as Pageable)
                >> allOffers

        when: "we run getOffersFiltered()"
        offerService.getOffersFiltered(filters, pageable)

        then: "findAll() is called once"
        1 * offerRepository.findAll(
                _ as org.springframework.data.jpa.domain.Specification<OfferEntity>,
                _ as Pageable)
                >> allOffers
    }

    def "getOffer() WHEN called with id, which is found in offerRepository SHOULD return offer with given id"() {
        given: "searched offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offerEntity)

        and: "mocked offerMapper"
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "getOffer() returns offer"
        def returnedOffer = offerService.getOffer(offerId)

        then: "returned offer has same id as given in method parameter"
        returnedOffer.getId() == offerId
    }

    def "getOffer() WHEN called with id, which is not found in offerRepository SHOULD throw OfferNotFoundException"() {
        given: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.empty()

        and: "mocked offerMapper"
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "we run getOffer()"
        offerService.getOffer(offerId)

        then: "there is thrown offerNotFoundException"
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
        offerRepository.findById(_ as Long) >> Optional.of(offerEntity)

        and: "mocked offerMapper"
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "we run getOffer()"
        offerService.getOffer(offerId)

        then: "findById() is called once"
        1 * offerRepository.findById(_) >> Optional.of(offerEntity)
        1 * offerMapper.fromEntity(_) >> offer
    }

    def "deleteOffer() WHEN called with id SHOULD call deleteById() method from offerRepository once"() {
        given: "id of the offer to delete"
        def offerId = 1

        when: "we run deleteOffer()"
        offerService.deleteOffer(offerId)

        then: "deleteById() is called once"
        1 * offerRepository.deleteById(_)
    }

    def "updateOffer() WHEN called with offer and existing id SHOULD return original updated offer of given id"() {
        given: "offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offerEntity)
        offerRepository.saveAndFlush(_ as OfferEntity) >> offerEntity

        and: "mocked offerMapper"
        offerMapper.toEntity(_ as Offer) >> offerEntity
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "updateOffer() returns offer with given offerId"
        def returnedOffer = offerService.updateOffer(updatedOffer, offerId)

        then: "returned offer is a same record which we saved first"
        offer.getDate() == returnedOffer.getDate()
        offer.getPrice() == returnedOffer.getPrice()
        offer.getDescription() == returnedOffer.getDescription()
        offer.getAvailability() == returnedOffer.getAvailability()
        offer.getId() == returnedOffer.getId()
    }

    def "updateOffer() WHEN called with offer and non-existing id SHOULD throw OfferNotFoundException"() {
        given: "offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.empty()

        and: "mocked offerMapper"
        offerMapper.toEntity(_ as Offer) >> offerEntity
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "updateOffer() returns offer with given offerId"
        offerService.updateOffer(updatedOffer, offerId)

        then: "there should be thrown OfferNotFoundException"
        def offerNotFoundException = thrown(OfferNotFoundException)
        offerNotFoundException.message == "There is no such offer with id: " + offerId
    }

    def "updateOffer() WHEN called with offer and id SHOULD call saveAndFlush() method of offerRepository once"() {
        given: "offer id"
        def offerId = 1

        and: "mocked offerRepository"
        offerRepository.findById(_ as Long) >> Optional.of(offerEntity)
        offerRepository.saveAndFlush(_ as OfferEntity) >> offerEntity

        and: "mocked offerMapper"
        offerMapper.toEntity(_ as Offer) >> offerEntity
        offerMapper.fromEntity(_ as OfferEntity) >> offer

        when: "we run updateOffer"
        offerService.updateOffer(updatedOffer, offerId)

        then: "saveAndFlush() is called once"
        1 * offerRepository.saveAndFlush(_) >> offerEntity
        1 * offerMapper.toEntity(_ as Offer) >> offerEntity
        2 * offerMapper.fromEntity(_ as OfferEntity) >> offer
    }

}
