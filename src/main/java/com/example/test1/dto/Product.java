package com.example.test1.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@AllArgsConstructor
//@NoArgsConstructor
@ToString
@Data
@Entity(name = "PRODUCT_CATALOG")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String productName;
    private String productDesc;
}
