package com.example.JwtAnanthan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // Enum: ADMIN, USER, etc.
}
