package com.example.JwtAnanthan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JwtAnanthan.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name); // Used to match JWT subject
}
