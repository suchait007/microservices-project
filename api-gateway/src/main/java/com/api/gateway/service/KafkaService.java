package com.api.gateway.service;


import com.api.gateway.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {


    @Value("${producer.topic.name}")
    private String producerTopicName;

    private final KafkaTemplate<String, ProductRequest> kafkaTemplate;

    public void sendProducts(ProductRequest productRequest) {
        kafkaTemplate.send(producerTopicName,productRequest);
        log.info("ProductRequest messages has been sent to Kafka topic.");
    }
}
