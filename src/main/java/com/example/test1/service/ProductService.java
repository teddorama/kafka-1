package com.example.test1.service;

import com.example.test1.config.Config;
import com.example.test1.dto.Product;
import com.example.test1.repository.ProductMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisCustomService redisCustomService;

    private int iterationCount;
    private int insertOneCount;

    public ProductService() {
        this.iterationCount = 0;
        this.insertOneCount = 0;
    }

    public void initIterationCount() {
        this.iterationCount = 0;
    }

    public List<Product> getAllProductList() {
        return productMapper.getAllProductList();
    }


    public void insertProduct(Product product) {
        if (Config.START_FLAG) {
            Config.START_FLAG = false;
            Config.printTime("Insert One Started - " + iterationCount + " : ", 1);
        }

        insertOneProduct(product);
        redisCustomService.insertReply(product);
        insertOneCount++;

//        redisCustomService.waitReply(product.getProductDesc());

        if (insertOneCount >= Config.BATCH_SIZE) {
            Config.printTime("Insert One Completed - " + iterationCount + " : ", insertOneCount);
            insertOneCount = 0;
            iterationCount++;
        }


    }

    @Transactional
    private void insertOneProduct(Product product) {
        productMapper.insertProduct(product);
    }

    @Transactional
    public void insertBulkProduct(List<Product> productList) {
        Config.printTime("Bulk Insert Started - " + iterationCount + " : ", productList.size());

        for (Product product : productList) {
            productMapper.insertProduct(product);
            redisCustomService.insertReply(product);
        }

        Config.printTime("Bulk Insert Completed - " + iterationCount + " : ", productList.size());
        iterationCount++;
    }
}
