package com.example.JwtAnanthan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.JwtAnanthan.model.User;
import com.example.JwtAnanthan.model.UserPrincipal;
import com.example.JwtAnanthan.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repo.findByUsername(username);
		
		
		if (user == null) {
			System.out.println("User 404");
			throw new UsernameNotFoundException("User 404");
		}
		log.info("user {}",user.toString());
		return new UserPrincipal(user);
	}

}