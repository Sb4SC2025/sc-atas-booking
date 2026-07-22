package com.semicore.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BookingConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean(name = "flightsWebClient")
    public WebClient flightsWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/flights/api/v1/load-flights")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean(name = "offersWebClient")
    public WebClient offersWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080/offers/api/v1/load-offers")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Bean(name = "flightsClient")
    public RestClient flightsClient() {
        return RestClient.create("http://localhost:9091/flights/api/v1/load-flights");
    }

    @Bean(name = "offersClient")
    public RestClient offersClient() {
        return RestClient.create("http://localhost:9092/offers/api/v1/load-offers");
    }
}
