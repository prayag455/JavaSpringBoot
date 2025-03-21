package com.example.JwtAnanthan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class PersonChoice {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Person person;

    private Long cartId;
}