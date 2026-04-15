package org.sparta.orderservice.kafka;

import org.junit.jupiter.api.Test;
import org.sparta.orderservice.service.KafkaProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KakfkaTest {

    @Autowired
    KafkaProduceService produceService;

    @Test
    void test() {
        produceService.sendMessage("test-topic", "안녕하세요.");
    }
}
