package com.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.model.Roles;
import com.demo.model.Users;
import com.demo.repo.RoleRepository;
import com.demo.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public UserService() {

	}

	public Optional<Users> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public Users saveUser(Users user) {
		user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
		user.setActive(true); 
		Roles userRole = roleRepository.findByRole("admin");
		user.setRoles(new HashSet<Roles>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
}
