package com.semicore.booking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    //url: http://localhost:9090/booking/api/v1/hello
    @GetMapping
    public String hello(){
        return "Hello SC ATAS Customer!";
    }
}
