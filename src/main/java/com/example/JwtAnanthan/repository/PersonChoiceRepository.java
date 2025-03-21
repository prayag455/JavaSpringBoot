package com.example.JwtAnanthan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JwtAnanthan.model.Person;
import com.example.JwtAnanthan.model.PersonChoice;

@Repository
public interface PersonChoiceRepository extends JpaRepository<PersonChoice, Long> {
    Optional<PersonChoice> findByPerson(Person person);
}
