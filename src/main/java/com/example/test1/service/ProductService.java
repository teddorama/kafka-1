package com.example.test1.service;

import com.example.test1.dto.Product;
import com.example.test1.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    public List<Product> getAllProductList() {
        return productMapper.getAllProductList();
    }
}
