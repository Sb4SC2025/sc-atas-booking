package com.semicore.booking.controllers;


import com.semicore.booking.dao.entities.FlightForSelection;
import com.semicore.booking.services.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightsController {

    @Autowired
    private FlightsService flightsService;

    @GetMapping("/flights")
    public List<String> getFlights() {
        // Logic to retrieve flights from the database or service
        return List.of("Flight 1", "Flight 2", "Flight 3");
    }

    @GetMapping("/flight-selection")
    public List<String> fetchFlightsForSelection(){
        return flightsService.fetchFlightsForSelection();
    }
}
