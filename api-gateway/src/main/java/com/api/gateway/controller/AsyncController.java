package com.api.gateway.controller;


import com.api.gateway.dto.ProductRequest;
import com.api.gateway.dto.RequestStatus;
import com.api.gateway.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/async")
public class AsyncController {

    private final KafkaService kafkaService;

    @PostMapping("/products")
    public ResponseEntity<RequestStatus> sendProducts(@RequestBody ProductRequest productRequest){
        log.info("Product request received : {} ", productRequest);

        try {
            kafkaService.sendProducts(productRequest);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RequestStatus("Failed"));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RequestStatus("Success"));
    }
}
