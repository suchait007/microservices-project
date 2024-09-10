package com.product.service;


import com.api.gateway.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProductsConsumer {


    private final ProductService productService;

    @Transactional
    @KafkaListener(topics = "${consumer.topic.name}")
    public void consumeProducts(ProductRequest productRequest) {

        log.info("Event received : {} ", productRequest.toString());
        productService.storeAllProducts(productRequest);
        log.info("All products have been processed into database.");
    }
}
