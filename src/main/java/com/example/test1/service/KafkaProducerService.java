package com.example.test1.service;

import com.example.test1.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    // Kafka 메시지를 전송하기 위한 템플릿 (문자열 키, 값)
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // kafka에 메시지 전송
    public void send(String topic, Product product) {

        // ObjectMapper를 통해 ProductRegistDto 객체를 JSON 문자열로 변환
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(product);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        // JSON 문자열을 해당 topic에 전달
        kafkaTemplate.send(topic, jsonInString);

        System.out.println("Kafka Producer sent data from the product micro service " + product);

    }
}
