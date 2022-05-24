package com.example.foode.offer


import com.example.foode.product.Product
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
            "product": {
                "name": "Apple",
                "expirationDate": "2030-02-10"
            }
        }
        """

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private OfferRepository offerRepository

    private Offer offer

    void setup() {
        offer = new Offer(
                BigDecimal.valueOf(30).setScale(0),
                LocalDate.of(2022, 03, 02),
                "newDesc",
                "avail",
                new Product(
                        "apple",
                        LocalDate.of(2030, 02, 10)))
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

    def "gets Offers by City"() throws Exception {
        given: "city id which offers are assigned to"
        def cityId = "1"

        when: "we perform get request with pageable parameters and city id"
        def result = mockMvc
                .perform(get("/api/offers?page=0&size=10")
                        .param("cityId", cityId))

        then: "response status is equal to Ok"
        result.andExpect(status().isOk())
    }

    def "gets Offer"() throws Exception {
        given: "offer id which we want to search for and firstly we save it to db"
        def savedOffer = offerRepository.saveAndFlush(offer)

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
        given:
        "offer id which we want to search for and " +
                "we delete it firstly from db to be sure there is no record with such id"
        def offerId = Long.valueOf(10000)

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
        given: "offer id we want to delete and firstly we save offer with such id to db"
        offerRepository.saveAndFlush(offer)

        def offerId = offer.getId()

        when: "we perform delete request with given id"
        def result = mockMvc
                .perform(delete(String.format(
                        "/api/offers/%d",
                        offerId)))

        then: "response status is equal to NoContent and there is no offer with given id in db"
        result.andExpect(status().isNoContent())

        assertThat(offerRepository.findAll())
                .extracting(
                        Offer.&getId as Function,
                        Offer.&getPrice as Function,
                        Offer.&getDate as Function,
                        Offer.&getDescription as Function,
                        Offer.&getAvailability as Function)
                .doesNotContain(tuple(
                        offer.getId(),
                        offer.getPrice(),
                        offer.getDate(),
                        offer.getDescription(),
                        offer.getAvailability()))
    }

    def "updates Offer"() throws Exception {
        given: "offer id we want to update and firstly we save offer with such id"
        offer.setDescription("oldDesc")
        offerRepository.saveAndFlush(offer)

        def offerId = offer.getId()

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
                        Offer.&getPrice as Function,
                        Offer.&getDate as Function,
                        Offer.&getDescription as Function,
                        Offer.&getAvailability as Function)
                .contains(tuple(
                        offer.getPrice(),
                        offer.getDate(),
                        "newDesc",
                        offer.getAvailability()))
    }

}
