package com.example.test1.dto;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor
@Data
@RedisHash(value = "product", timeToLive = 1000)
public class Reply {

    private String id;
    private String value;
    private Product product;
}
