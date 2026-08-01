package com.semicore.booking.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "flightsclient", url="${sc.atas.booking.flight-service.url}")
public interface FlightsClient {
    @GetMapping("/load-flights")
    public String loadFlights();
}
