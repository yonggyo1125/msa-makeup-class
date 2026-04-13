package org.sparta.orderservice.config;

import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

// RoundRobinLoadBalancer, RandomLoadBalancer
@Component
public class CustomLoadBalancerConfig {
    @Bean
    public ReactorServiceInstanceLoadBalancer loadBalancedReactorServiceInstance(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }

    // 가중치 기반
    @Bean
    public ServiceInstanceListSupplier weightedServiceInstanceListSupplier(
            ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient() // WebMvc(Servlet) 환경이므로 Blocking 클라이언트 사용
                .withWeighted() // 가중치 기능 활성화
                .withCaching()  // 캐싱 추가
                .build(context);
    }
}
