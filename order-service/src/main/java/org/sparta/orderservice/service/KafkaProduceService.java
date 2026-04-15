package org.sparta.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProduceService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key1", message);
        record.headers().add("testheader", "testvalue".getBytes());

        kafkaTemplate.send(record)
                .whenComplete((records, e) -> {
                    if (e == null) {
                        // 메세지 전송 성공
                        log.info("메세지 전송 성공");
                    } else { // 메세지 전송 싪패
                        log.error("메세지 전송 실패: {}", e.getMessage());
                    }
                });
    }
}
