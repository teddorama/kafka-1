package com.example.test1.service;

import com.example.test1.config.Config;
import com.example.test1.dto.Product;
import com.example.test1.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

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
        insertOneCount++;

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
        }

        Config.printTime("Bulk Insert Completed - " + iterationCount + " : ", productList.size());
        iterationCount++;
    }
}
