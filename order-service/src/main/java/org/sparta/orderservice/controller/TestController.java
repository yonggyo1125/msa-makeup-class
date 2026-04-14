package org.sparta.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.sparta.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final OrderService orderService;

    @GetMapping("/test5/{num}")
    public String test5(@PathVariable("num") int num) {
        //return orderService.process1(num);
        //return orderService.process2(num);
        return orderService.process3(num);
    }
}
