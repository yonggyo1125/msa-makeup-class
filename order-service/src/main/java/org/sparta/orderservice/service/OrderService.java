package org.sparta.orderservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @CircuitBreaker(name="order-service", fallbackMethod = "fallback1")
    public String process1(int num) {
        if (num == 10) {
            throw new RuntimeException("예외 발생");
        }

        return "정상 처리:" + num;
    }

    public String fallback1(int num, Throwable e) {
        log.error("폴백1 호출: {}", e.getMessage(), e);
        return "폴백1:" + num;
    }

    @Retry(name="order-service", fallbackMethod = "fallback2")
    public String process2(int num) {
        log.info("Retry...");
        if (num == 10) {
            throw new RuntimeException("예외 발생");
        }

        return "정상 처리:" + num;
    }

    public String fallback2(int num, Throwable e) {
        log.error("폴백2 호출: {}", e.getMessage());
        return "폴백2:" + num;
    }

    @RateLimiter(name="order-service", fallbackMethod = "fallback3")
    public String process3(int num) {
        log.info("RateLimit...");
        if (num == 10) {
            throw new RuntimeException("예외 발생");
        }

        return "정상 처리:" + num;
    }

    public String fallback3(int num, Throwable e) {
        log.error("폴백3 호출: {}", e.getMessage());
        return "폴백3:" + num;
    }

    // Retry -> CircuitBreaker -> RateLimiter -> Bulkhead
//    @Retry(name="order-service", fallbackMethod = "fallback4")
//    @CircuitBreaker(name="order-service", fallbackMethod = "fallback4")
//    @RateLimiter(name="order-service", fallbackMethod = "fallback4")
//    @Bulkhead(name="order-service", fallbackMethod = "fallback4")
//    public String process4(int num) {
//        log.info("RateLimit...");
//        if (num == 10) {
//            throw new RuntimeException("예외 발생");
//        }
//
//        return "정상 처리:" + num;
//    }

    public String fallback4(int num, Throwable e) {
        log.error("폴백3 호출: {}", e.getMessage());
        return "폴백3:" + num;
    }
}
