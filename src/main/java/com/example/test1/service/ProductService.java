package com.example.test1.service;

import com.example.test1.config.Config;
import com.example.test1.dto.Product;
import com.example.test1.mapper.ProductMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public List<Product> getAllProductList() {
        return productMapper.getAllProductList();
    }

    public void insertProduct(Product product) {

        productMapper.insertProduct(product);

        Config.printTime("Insert One ", 1);
    }

    public void insertBulkProduct(List<Product> productList) {

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

        for (Product product : productList) {
            productMapper.insertProduct(product);
        }

        Config.printTime("Bulk Insert ", productList.size());

        sqlSession.flushStatements();
        sqlSession.commit();
    }
}
