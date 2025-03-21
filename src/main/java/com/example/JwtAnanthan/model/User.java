package com.example.JwtAnanthan.model;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name = "users")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
	
	 @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
	 @CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"))
	    @Enumerated(EnumType.STRING)
	    private Set<Role> roles; 
	

}