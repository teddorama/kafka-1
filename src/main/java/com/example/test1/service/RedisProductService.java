package com.example.test1.service;

import com.example.test1.dto.Product;
import com.example.test1.dto.Reply;
import com.example.test1.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProductService {
    private final RedisRepository redisRepository;

    public void create(Reply product) {
        redisRepository.save(product);
    }

    public Reply read(String key) {
        return redisRepository.findById(key).get();
    }

    public void update(Reply product) {
        redisRepository.save(product);
    }

    public void delete(String key) {
        redisRepository.deleteById(key);
    }
}
