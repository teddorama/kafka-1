package com.example.test1.mapper;

import com.example.test1.dto.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getAllProductList();
    void insertProduct(Product product);
}
