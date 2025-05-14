package com.example.test1.controller;

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

    private long id = 0;

    @GetMapping
    public String sample() {
        return "Sample~";
    }

    @GetMapping("/send/{productName}/{productDesc}")
    public void send(@PathVariable String productName, @PathVariable String productDesc) {
        Product product = new Product(id++, productName, productDesc);

        kafkaProducerService.send("testTopic1", product);
    }

    @GetMapping("/get")
    public String get() {
        return productService.getAllProductList().toString();
    }


}
