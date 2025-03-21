package com.example.JwtAnanthan.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.JwtAnanthan.model.Role;
import com.example.JwtAnanthan.model.User;
import com.example.JwtAnanthan.model.UserDTO;
import com.example.JwtAnanthan.service.JwtService;
import com.example.JwtAnanthan.service.MyUserDetailsService;
import com.example.JwtAnanthan.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public User register(@RequestBody UserDTO userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setRoles(userDto.getRoles().stream()
				.map(Role::valueOf)
				.collect(Collectors.toSet()));
		
	  return service.saveUser(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user){

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		if(authentication.isAuthenticated()) {
			UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());
			return jwtService.generateToken(user.getUsername(), userDetails.getAuthorities()
					.stream().map(grantedAuthority -> Role.valueOf(grantedAuthority.getAuthority()))
					.collect(Collectors.toSet()));
		}
		else {
			return "Login Failed";
		}
	}

	 @GetMapping("/verify/user")
	    public String verifyToken(@RequestHeader("Authorization") String authHeader) {
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);
	            String username = jwtService.extractUserName(token);

	            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
	            // Validate the token
	            if (username != null && jwtService.validateToken(token, userDetails)) {
	                return "user are verified";
	            }
	        }
	        return "Invalid token";
	    }
	 
	 @GetMapping("/verify/manager")
	    public String verifyMgrToken(@RequestHeader("Authorization") String authHeader) {
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);
	            String username = jwtService.extractUserName(token);
	            
	            System.out.println(username);
	            log.info("username extracted {}",username);
	            log.info("extracting username ...............");
	            
	            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
	            // Validate the token
	            if (username != null && jwtService.validateToken(token, userDetails)) {
	                return "manager are verified";
	            }
	        }
	        return "Invalid token";
	    }
}