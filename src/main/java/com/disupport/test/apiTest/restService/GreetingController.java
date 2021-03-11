package com.disupport.test.apiTest.restService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String temlate = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(
            @RequestParam(name = "name", defaultValue = "World")
                    String name
    ) {
        return new Greeting(counter.incrementAndGet(), String.format(temlate, name));
    }

    @GetMapping(path = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getQuote() {
        String baseUrl = "https://quoters.apps.pcfone.io";
        WebClient webClient = WebClient.create(baseUrl);
        GeetingRestClient geetingRestClient = new GeetingRestClient(webClient);
        String response = geetingRestClient.retrieveQuote();
        return new ResponseEntity<Object>
                (response, HttpStatus.OK);
    }
}
