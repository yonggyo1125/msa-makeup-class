package org.sparta.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="product-service", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {
    @GetMapping("/{seq}/details")
    String getProduct(@PathVariable("seq") int seq);

    @PostMapping(path="/testdata", consumes = MediaType.APPLICATION_JSON_VALUE)
    String requestTestData(@RequestBody TestData testData);
}