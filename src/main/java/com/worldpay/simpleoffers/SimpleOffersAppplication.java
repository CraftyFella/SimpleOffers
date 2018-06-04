package com.worldpay.simpleoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class SimpleOffersAppplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SimpleOffersAppplication.class, args);
    }

    @Bean
    public OffersStore offersStore() {
        return new InMemoryOffersStore();
    }
}

