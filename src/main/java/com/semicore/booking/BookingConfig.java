package com.semicore.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookingConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "flightsClient")
    public RestClient flightsClient() {
        return RestClient.create("http://localhost:9091/flights/api/v1/load-flights");
    }

    @Bean(name = "offersClient")
    public RestClient offersClient() {
        return RestClient.create("http://localhost:9091/offers/api/v1/load-offers");
    }
}
