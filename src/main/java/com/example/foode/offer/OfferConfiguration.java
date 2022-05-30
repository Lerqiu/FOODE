package com.example.foode.offer;

import com.example.foode.offer.service.OfferMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class OfferConfiguration {
    @Bean
    OfferMapper offerMapper() {
        return Mappers.getMapper(OfferMapper.class);
    }
}
