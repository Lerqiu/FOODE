package com.example.foode.Offer


import com.example.foode.User.User
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
class OfferControllerSpock extends Specification {

    private final String offerBody = """
        {
            "price": 30,
            "date": "2022-03-02",
            "description": "desc",
            "availability": "avail",
            "user": {
                "login": "login",
                "password": "passwd",
                "points": 50,
                "contact": "contact",
                "offers": []
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
                BigDecimal.valueOf(30).setScale(2),
                LocalDate.of(2022, 03, 02),
                "newDesc",
                "avail",
                new User("login",
                        "passwd",
                        BigDecimal.valueOf(50),
                        "contact",
                        new ArrayList<>()))
    }

    def "creates Offer"() throws Exception {
        when:
        def result = mockMvc
                .perform(post("/api/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(offerBody))

        then:
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
                        offer.getDescription(),
                        offer.getAvailability()))
    }

    def "gets Offers by City"() throws Exception {
        given:
        def cityId = "1"

        when:
        def result = mockMvc
                .perform(get("/api/offers?page=1&size=10")
                        .param("cityId", cityId))

        then:
        result.andExpect(status().isOk())
    }

    def "gets Offer"() throws Exception {
        given:
        def offerId = 10000

        offer.setId(offerId)
        offerRepository.saveAndFlush(offer)

        when:
        def result = mockMvc
                .perform(get(String.format(
                        "/api/offers/%d",
                        offerId)))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("price").value(offer.getPrice()))
                .andExpect(jsonPath("date").value(offer.getDate()))
    }

    def "returns NOT_FOUND response status while offer with given id isn't persisted in db"() {
        given:
        def offerId = 10000

        offer.setId(offerId)
        offerRepository.delete(offer)

        when:
        def result = mockMvc
                .perform(get(String.format(
                        "/api/offers/%d",
                        offerId)))

        then:
        result.andExpect(status().isNotFound())
    }

    def "deletes Offer"() throws Exception {
        given:
        def offerId = 10000

        offer.setId(offerId)
        offerRepository.saveAndFlush(offer)

        when:
        def result = mockMvc
                .perform(delete(String.format(
                        "/api/offers/%d",
                        offerId)))

        then:
        result.andExpect(status().isNoContent())

        assertThat(offerRepository.findAll())
                .extracting(
                        Offer.&getId as Function,
                        Offer.&getPrice as Function,
                        Offer.&getDate as Function,
                        Offer.&getDescription as Function,
                        Offer.&getAvailability as Function)
                .doesNotContain(tuple(
                        offer.getPrice(),
                        offer.getDate(),
                        offer.getDescription(),
                        offer.getAvailability()))
    }

    def "updates Offer"() throws Exception {
        given:
        def offerId = 10000

        offer.setId(offerId)
        offer.setDescription("oldDesc")
        offerRepository.saveAndFlush(offer)

        when:
        def result = mockMvc
                .perform(put(String.format(
                        "/api/offers/%d",
                        offerId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(offerBody))

        then:
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
