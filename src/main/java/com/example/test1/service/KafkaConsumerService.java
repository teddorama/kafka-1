package com.example.test1.service;

import com.example.test1.config.Config;
import com.example.test1.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaConsumerService {

    @Autowired
    ProductService productService;

    List<Product> productList;

    public KafkaConsumerService() {
        this.productList = new ArrayList<Product>();
    }

    @KafkaListener(groupId = "myGroup1", topics = Config.PRODUCT_TOPIC)
    public void test(ConsumerRecord<String, String> data, Acknowledgment acknowledgment, Consumer<String, String> consumer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(data.value(), Product.class);

        productService.insertProduct(product);

        acknowledgment.acknowledge();
    }


    @KafkaListener(groupId = "myGroup2", topics = Config.BULK_PRODUCT_TOPIC)
    public void test2(ConsumerRecord<String, String> data, Acknowledgment acknowledgment, Consumer<String, String> consumer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(data.value(), Product.class);

        productList.add(product);
        if (productList.size() >= Config.BATCH_SIZE) {

            productService.insertBulkProduct(productList);

            productList.clear();
            acknowledgment.acknowledge(); // 여기서 다른 topic 전송 - reids session id + 주문번호 + 성공여부 등
        }
    }

}
