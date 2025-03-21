package com.example.JwtAnanthan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    private String productName;
    private String description;
}
