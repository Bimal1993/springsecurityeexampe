package com.demo.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.config.UserDetailsImpl;
import com.demo.model.Role;
import com.demo.model.User;
import com.demo.repo.RoleRepository;
import com.demo.repo.UsersRepository;

@Service
public class UserService {
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	String roles;

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	

	public UserDetails findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) { 
			throw new UsernameNotFoundException("User Not found");
		} else {  
			return new UserDetailsImpl(user); 
		} 
	} 

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("admin");
		user.setRole(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
}
