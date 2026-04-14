package org.sparta.orderservice.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ProductClientTest {
    @Autowired
    ProductClient client;

    @Test
    void test1() {
        log.info("result: {}", client.getProduct(100));
    }

    @Test
    void test2() {
        log.info("result: {}", client.requestTestData(new TestData("값1", "값2")));
    }

}
