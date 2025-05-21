package com.example.test1.controller;

import com.example.test1.config.Config;
import com.example.test1.dto.Product;
import com.example.test1.service.KafkaProducerService;
import com.example.test1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping
    public String sample() {
        Config.START_FLAG = true;
        productService.initIterationCount();

        return "Start Flag init completed!";
    }

    //DB 단건 Insert
    @GetMapping("/senddb/{productName}/{productDesc}")
    public void sendDb(@PathVariable String productName, @PathVariable String productDesc) {
        Product product = new Product(0L, productName, productDesc);

        productService.insertProduct(product);
    }

    //Kafka 단건 Insert
    @GetMapping("/send/{productName}/{productDesc}")
    public void sendKafka(@PathVariable String productName, @PathVariable String productDesc) {
        Product product = new Product(0L, productName, productDesc);

        kafkaProducerService.send(Config.PRODUCT_TOPIC, product);
    }

    //Kafka Bulk Insert
    @GetMapping("/bulksend/{productName}/{productDesc}")
    public void bulksendKafka(@PathVariable String productName, @PathVariable String productDesc) {
        Product product = new Product(0L, productName, productDesc);

        kafkaProducerService.send(Config.BULK_PRODUCT_TOPIC, product);
    }

    @GetMapping("/get")
    public String get() {
        return productService.getAllProductList().toString();
    }

}
