package com.example.test1.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity(name = "PRODUCT_CATALOG")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productName;
    private String productDesc;
}
