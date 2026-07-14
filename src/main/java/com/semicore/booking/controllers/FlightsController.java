package com.semicore.booking.controllers;


import com.semicore.booking.dao.entities.FlightForSelection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FlightsController {

    @GetMapping("/flights")
    public List<String> getFlights() {
        // Logic to retrieve flights from the database or service
        return List.of("Flight 1", "Flight 2", "Flight 3");
    }

    @GetMapping("/flight-selection")
    public List<FlightForSelection> flightForSelection(){
        return List.of(new FlightForSelection[]{
                new FlightForSelection(1001L, "QR501", "HYD", "DOH"),
                new FlightForSelection(1002L, "QR4775", "HYD", "DOH"),
                new FlightForSelection(1003L, "QR4777", "HYD", "DOH")
        });
    }
}
