package com.semicore.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScAtasBookingApplication {

	static void main(String[] args) {
		SpringApplication.run(ScAtasBookingApplication.class, args);
	}

}
