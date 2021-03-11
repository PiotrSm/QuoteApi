package com.disupport.test.apiTest.restService;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

public class GeetingRestClient {

    private static final String GET_QUOTE ="/api/random" ;
    private WebClient webClient;

    public GeetingRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String  retrieveQuote() {
        return webClient.get().uri(GET_QUOTE)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
