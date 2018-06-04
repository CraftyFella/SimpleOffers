package com.worldpay.simpleoffers;

import com.worldpay.simpleoffers.query.OfferByOfferId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@SpringBootApplication
@Configuration
public class SimpleOffersAppplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleOffersAppplication.class, args);
    }

    @Bean public Map<UUID, Offer> db(){
        return new HashMap<>();
    }

    @Bean
    public OffersStore offersStore(Map<UUID, Offer> db) {
        return new InMemoryOffersStore(db);
    }

    @Bean
    public OfferByOfferId offerByOfferId(Map<UUID, Offer> db) {
        return new InMemoryOfferByOfferId(db);
    }
}

