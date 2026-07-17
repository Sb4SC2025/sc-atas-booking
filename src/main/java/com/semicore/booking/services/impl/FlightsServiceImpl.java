package com.semicore.booking.services.impl;

import com.semicore.booking.services.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightsServiceImpl implements FlightsService {

   // @Autowired
   // private RestTemplate restTemplate;

    @Autowired
    private RestClient flightsClient;
    @Autowired
    private RestClient offersClient;

    @Override
    public List<String> fetchFlightsForSelection() {
        List<String> responses = new ArrayList<>();
        String flightsResponse = "";
        String offersResponse = "";
        //RestTemplate restTemplate = new RestTemplate();
        /*
        flightsResponse = restTemplate.exchange("http://localhost:9091/flights/api/v1/load-flights",
                HttpMethod.GET, null, String.class).getBody();
        offersResponse = restTemplate.exchange("http://localhost:9092/offers/api/v1/load-offers",
                HttpMethod.GET, null, String.class).getBody();
         */
        //flightsResponse = flightsClient.get().toString();
        flightsResponse = flightsClient.method(HttpMethod.GET).body(flightsResponse).retrieve().requiredBody(String.class);
        offersResponse = offersClient.get().retrieve().requiredBody(String.class);

        responses.add(flightsResponse);
        responses.add(offersResponse);
        return responses;
    }
}
