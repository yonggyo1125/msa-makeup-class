package org.sparta.orderservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class OrderController {

    private final RestTemplate restTemplate;

    private final RestClient.Builder builder;

    public OrderController(RestTemplate restTemplate, @Qualifier("loadedBalancedRestClient") RestClient.Builder builder) {
        this.restTemplate = restTemplate;
        this.builder = builder;
    }

    @Value("${message.test1}")
    private String test1;

    @Value("${message.test2}")
    private String test2;

    @GetMapping("/test1")
    public String test1() {
        return "%s, %s".formatted(test1, test2);
    }

    @GetMapping("/test2")
    public String test2() {
        return restTemplate.getForObject("http://product-service/1/details", String.class);

    }

    @GetMapping("/test3")
    public ResponseEntity<String> test3() {
        return builder.baseUrl("http://product-service/1/details")
                .build()
                .get()
                .retrieve()
                .toEntity(String.class);
    }
}
