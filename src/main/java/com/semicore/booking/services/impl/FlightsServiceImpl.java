package com.semicore.booking.services.impl;

import com.semicore.booking.clients.FlightsClient;
import com.semicore.booking.clients.OffersClient;
import com.semicore.booking.services.FlightsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class FlightsServiceImpl implements FlightsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightsServiceImpl.class);
   // @Autowired
   // private RestTemplate restTemplate;

    @Autowired
    private RestClient flightsClient;
    @Autowired
    private RestClient offersClient;

    @Qualifier("offersWebClient")
    @Autowired
    private WebClient offersWebClient;

    @Qualifier("flightsWebClient")
    @Autowired
    private WebClient flightsWebClient;

    @Value("${sb.sample.message:default message}")
    private String message;

    @Autowired
    private FlightsClient flightsFeignClient;

    @Autowired
    private OffersClient offersFeignClient;

    @CircuitBreaker(
            name = "flightsSelectionService",
            fallbackMethod = "fallbackFetchFlightsForSelection")
    @Override
    public List<String> fetchFlightsForSelection(){
        List<String> responses = new ArrayList<>();
        LOGGER.info("Fetching flights and offers for selection...");
        /*    String flightsResponse = flightsClient.method(HttpMethod.GET).retrieve().body(String.class) + message;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String offersResponse = offersClient.method(HttpMethod.GET).retrieve().body(String.class) + message;

            responses.add(flightsResponse);
            responses.add(offersResponse);*/

        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        //ExecutorService executorService = Executors.newCachedThreadPool();
       /* ExecutorService executorService = Executors.newScheduledThreadPool(5);
        Future<?> flightsServiceResponse = executorService.submit(() -> {
            flightsClient.method(HttpMethod.GET).retrieve().body(String.class);
        });
        Future<?> offersServiceResponse = executorService.submit(() -> {
            offersClient.method(HttpMethod.GET).retrieve().body(String.class);
        });
        try {
            Thread.sleep(2000);
            responses.add((String) flightsServiceResponse.get());
            responses.add((String) offersServiceResponse.get());

        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching flights and offers", e);
            throw new RuntimeException("Error occurred while fetching flights and offers", e);
        } finally {
            executorService.shutdown();
        }*/
        //FeignClient implementation
        responses.add(flightsFeignClient.loadFlights());
        try{
            Thread.sleep(2000);
        }catch (Exception e){
        }
        responses.add(offersFeignClient.loadOffers());
        return  responses;
    }

    /**
     * The fallback method name can be anything, but the return type must be same as of the original method
     * and the parameters should match with original method with an extra parameter of type Exception
     */

    public List<String> fallbackFetchFlightsForSelection(Exception ex) {
        LOGGER.error("Error occurred while fetching flights for selection", ex);
        return List.of("Service unavailable : " + ex.getMessage());
    }

    /*@Override
    public List<String> fetchFlightsForSelection() {
        List<String> responses = new ArrayList<>();
        String flightsResponse = "";
        String offersResponse = "";
        //RestTemplate restTemplate = new RestTemplate();

       // flightsResponse = restTemplate.exchange("http://localhost:9091/flights/api/v1/load-flights",
       //         HttpMethod.GET, null, String.class).getBody();
       // offersResponse = restTemplate.exchange("http://localhost:9092/offers/api/v1/load-offers",
        //        HttpMethod.GET, null, String.class).getBody();

        //flightsResponse = flightsClient.get().toString();
        //flightsResponse = flightsClient.method(HttpMethod.GET).body(flightsResponse).retrieve().requiredBody(String.class);
        //offersResponse = offersClient.get().retrieve().requiredBody(String.class);
       try {
           // 1. Create a client instance
           HttpClient client = HttpClient.newHttpClient();

           // 2. Build the request for flights service
           HttpRequest flightsRequest = HttpRequest.newBuilder()
                   .uri(URI.create("http://localhost:9091/flights/api/v1/load-flights"))
                   .GET()
                   .build();
           // 3. Send the request and handle the body as a String
           HttpResponse<String> flightsHttpResponse = client.send(flightsRequest, HttpResponse.BodyHandlers.ofString());
           responses.add(flightsHttpResponse.body());

           //4. Build the request for offers service
           HttpRequest offersRequest = HttpRequest.newBuilder()
                   .uri(URI.create("http://localhost:9092/offers/api/v1/load-offers"))
                   .GET()
                   .build();
           //5. Send the request and handle the bodey as a String
           HttpResponse<String> offersHttpResponse = client.send(offersRequest, HttpResponse.BodyHandlers.ofString());
           responses.add(offersHttpResponse.body());
       } catch (Exception e) {
           e.printStackTrace();
       }
        return responses;
    }*/
}
