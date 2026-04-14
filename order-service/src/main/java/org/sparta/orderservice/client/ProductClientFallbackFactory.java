package org.sparta.orderservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {
    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {
            @Override
            public String getProduct(int seq) {
                log.error("Fallback getProduct: {}", cause.getMessage(), cause);
                return "폴백: getProduct";
            }

            @Override
            public String requestTestData(TestData testData) {
                log.error("Fallback requestTestData: {}", cause.getMessage(), cause);
                return "폴백: requestTestData";
            }
        };
    }
}
