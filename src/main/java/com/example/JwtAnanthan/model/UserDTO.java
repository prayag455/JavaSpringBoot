package com.example.JwtAnanthan.model;

import java.util.List;

import lombok.Data;

@Data
	public class UserDTO {
	    private String username;
	    private String password;
	    private List<String> roles; // Accept roles as a list of strings
	}

