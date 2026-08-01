package com.semicore.booking.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "offersclient", url="${sc.atas.booking.offer-service.url}")
public interface OffersClient {
    @GetMapping("/load-offers")
    public String loadOffers();

    @GetMapping("/hello")
    public String hello();
}
