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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
                "desc",
                "avail",
                new User("login",
                        "passwd",
                        BigDecimal.valueOf(50),
                        "contact",
                        new ArrayList<>()
                ))
    }

    def "creates Offer"() throws Exception {
        expect:
        mockMvc
                .perform(post("/api/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(offerBody))
                .andExpect(status().isCreated())

        assertThat(offerRepository.findAll())
                .extracting(
                        Offer.&getPrice as Function,
                        Offer.&getDate as Function,
                        Offer.&getDescription as Function,
                        Offer.&getAvailability as Function)
                .contains(tuple(offer.getPrice(), offer.getDate(), offer.getDescription(), offer.getAvailability()))
    }

}
