package com.example.foode.offer.presentation

import com.example.foode.city.persistence.CityEntity
import com.example.foode.city.persistence.CityRepository
import com.example.foode.offer.persistence.OfferEntity
import com.example.foode.offer.persistence.OfferRepository
import com.example.foode.product.persistence.ProductEntity
import com.example.foode.user.User
import com.example.foode.user.UserRepository
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDate
import java.util.function.Function

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.tuple
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@AutoConfigureDataJpa
class OfferControllerSpec extends Specification {

    private static final String OFFER_BODY = """
        {
            "price": 30,
            "date": "2022-03-02",
            "description": "newDesc",
            "availability": "avail",
            "city": {
                "id": 1,
                "name": "Wroclaw"
            },
            "product": {
                "name": "Apple",
                "expirationDate": "2030-02-10"
            },
            "userOutput": {
                "id": 1,
                "login": "log",
                "contact": "cont"
            }
        }
        """

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private OfferRepository offerRepository

    @Autowired
    private CityRepository cityRepository

    @Autowired
    private UserRepository userRepository

    private OfferEntity offer

    private CityEntity city

    private CityEntity secondCity

    private User user

    void setup() {
        city = new CityEntity("Wrocław")
        secondCity = new CityEntity("Warszawa")
        cityRepository.saveAllAndFlush(List.of(city, secondCity))

        user = new User(
                1,
                "loginTest",
                "PasswdTest",
                BigDecimal.valueOf(50),
                "contactTest",
                new ArrayList<>()
        )

        userRepository.saveAndFlush(user)

        offer = new OfferEntity(
                BigDecimal.valueOf(30).setScale(0),
                city,
                LocalDate.of(2022, 03, 02),
                "newDesc",
                "avail",
                new ProductEntity(
                        "apple",
                        LocalDate.of(2030, 02, 10)),
                user)
    }

    def "creates Offer"() throws Exception {
        when: "we perform post request with offer given in body"
        def result = mockMvc
                .perform(post("/api/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OFFER_BODY))

        def resultId = Long.valueOf(
                JsonPath.read(
                        result.andReturn()
                                .getResponse()
                                .getContentAsString(),
                        "id").toString())

        def createdOffer = offerRepository.findById(resultId).get()

        then: "response status is equal to Created and we can find created offer in db"
        result.andExpect(status().isCreated())

        createdOffer.getAvailability() == offer.availability
        createdOffer.getDescription() == offer.getDescription()
        createdOffer.getPrice() == offer.getPrice()
        createdOffer.getDate() == offer.getDate()
    }

    def "gets Offers filtered"() throws Exception {
        given: "product name"
        def name = "app"

        and: "city id"
        def cityId = city.getId().toString()

        and: "price from"
        def priceFrom = "2"

        and: "price to"
        def priceTo = "3"

        and: "example offers"
        def offers = exampleOffers()

        and: "filtered offer"
        def filteredOffer = offers.get(2)

        and: "offers saved to db"
        offerRepository.saveAllAndFlush(offers)

        when: "we perform get request with pageable parameters and filters"
        def result = mockMvc
                .perform(get("/api/offers")
                        .param("page", "0")
                        .param('size', "10")
                        .param("name", name)
                        .param("cityId", cityId)
                        .param("priceFrom", priceFrom)
                        .param("priceTo", priceTo))

        then: "response status is equal to OK and offers has been filtered"
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("content.length()").value(1))
                .andExpect(jsonPath("content[0].id").value(filteredOffer.getId()))
                .andExpect(jsonPath("content[0].price").value(filteredOffer.getPrice()))
                .andExpect(jsonPath("content[0].date").value(filteredOffer.getDate().toString()))
                .andExpect(jsonPath("content[0].description").value(filteredOffer.getDescription()))
                .andExpect(jsonPath("content[0].availability").value(filteredOffer.getAvailability()))
    }

    def "gets Offer"() throws Exception {
        given: "firstly we save it to db"
        def savedOffer = offerRepository.saveAndFlush(offer)

        and: "offer id which we want to search for"
        def offerId = savedOffer.getId()

        when: "we perform get request with given id"
        def result = mockMvc
                .perform(get(String.format(
                        "/api/offers/%d",
                        offerId)))

        then: "response status is equal to Ok and offer has same parameters as saved one"
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("id").value(offer.getId()))
                .andExpect(jsonPath("price").value(offer.getPrice()))
                .andExpect(jsonPath("date").value(offer.getDate().toString()))
                .andExpect(jsonPath("description").value(offer.getDescription()))
                .andExpect(jsonPath("availability").value(offer.getAvailability()))
    }

    def "returns NOT_FOUND response status while offer with given id isn't persisted in db"() throws Exception {
        given: "offer id which we want to search for"
        def offerId = Long.valueOf(10000)

        and: "we delete it firstly from db to be sure there is no record with such id"
        offerRepository.findById(offerId)
                .ifPresent(() -> offerRepository.deleteById(offerId))

        when: "we perform get request with given id"
        def result = mockMvc
                .perform(get(String.format(
                        "/api/offers/%d",
                        offerId)))

        then: "response status is equal to NotFound"
        result.andExpect(status().isNotFound())
    }

    def "deletes Offer"() throws Exception {
        given: "firstly we save offer"
        def savedOffer = offerRepository.saveAndFlush(offer)

        and: "offer id we want to delete"
        def offerId = savedOffer.getId()

        when: "we perform delete request with given id"
        def result = mockMvc
                .perform(delete(String.format(
                        "/api/offers/%d",
                        offerId)))

        then: "response status is equal to NoContent and there is no offer with given id in db"
        result.andExpect(status().isNoContent())

        assertThat(offerRepository.findAll())
                .extracting(
                        OfferEntity.&getId as Function,
                        OfferEntity.&getPrice as Function,
                        OfferEntity.&getDate as Function,
                        OfferEntity.&getDescription as Function,
                        OfferEntity.&getAvailability as Function)
                .doesNotContain(tuple(
                        offer.getId(),
                        offer.getPrice(),
                        offer.getDate(),
                        offer.getDescription(),
                        offer.getAvailability()))
    }

    def "updates Offer"() throws Exception {
        given: "firstly we save offer with old description"
        offer.setDescription("oldDesc")
        def savedOffer = offerRepository.saveAndFlush(offer)

        and: "id of the offer we want to update"
        def offerId = savedOffer.getId()

        when: "we perform put request with given id and updated offer body"
        def result = mockMvc
                .perform(put(String.format(
                        "/api/offers/%d",
                        offerId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OFFER_BODY))

        then: "response status is equal to Created and we can find updated offer in db"
        result.andExpect(status().isCreated())

        assertThat(offerRepository.findAll())
                .extracting(
                        OfferEntity.&getPrice as Function,
                        OfferEntity.&getDate as Function,
                        OfferEntity.&getDescription as Function,
                        OfferEntity.&getAvailability as Function)
                .contains(tuple(
                        offer.getPrice(),
                        offer.getDate(),
                        "newDesc",
                        offer.getAvailability()))
    }

    private List<OfferEntity> exampleOffers() {
        return List.of(
                new OfferEntity(
                        BigDecimal.valueOf(1).setScale(0),
                        city,
                        LocalDate.of(2022, 03, 02),
                        "newDesc",
                        "avail",
                        new ProductEntity(
                                "apple",
                                LocalDate.of(2030, 02, 10)),
                        user
                ),
                new OfferEntity(
                        BigDecimal.valueOf(2).setScale(0),
                        city,
                        LocalDate.of(2022, 03, 02),
                        "newDesc",
                        "avail",
                        new ProductEntity(
                                "banana",
                                LocalDate.of(2030, 02, 10)),
                        user
                ),
                new OfferEntity(
                        BigDecimal.valueOf(3).setScale(0),
                        city,
                        LocalDate.of(2022, 03, 02),
                        "newDesc",
                        "avail",
                        new ProductEntity(
                                "apple",
                                LocalDate.of(2030, 02, 10)),
                        user
                ),
                new OfferEntity(
                        BigDecimal.valueOf(3).setScale(0),
                        secondCity,
                        LocalDate.of(2022, 03, 02),
                        "newDesc",
                        "avail",
                        new ProductEntity(
                                "apple",
                                LocalDate.of(2030, 02, 10)),
                        user
        ))
    }
}
