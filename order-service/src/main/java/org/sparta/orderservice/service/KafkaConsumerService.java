package org.sparta.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "test-group-2")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            String key = record.key();
            String value = record.value();

            log.info("Received key: {}, value: {}, headers: {}", key, value, record.headers());

            ack.acknowledge();
        } catch (Exception e) {
            log.error("메세지 수신 실패, 재시도 시작: {}", e.getMessage());
            throw e; // 재시도 가능
        }
    }

    @KafkaListener(topics = "test-topic.DLT", groupId = "test-group-1")
    public void handleDLT(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("재시도 모두 실패, DLT 처리, 수동처리");
    }
}
