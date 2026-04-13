package org.sparta.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/{seq}/details")
    public String getProduct(@PathVariable("seq") String seq) {
        return "포트: %s, 상품 번호: %s".formatted(serverPort, seq);
    }
}
