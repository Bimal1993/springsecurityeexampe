package com.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.model.Roles;
import com.demo.model.User;
import com.demo.repo.RoleRepository;
import com.demo.repo.UsersRepository;

@Service
public class UserService {
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public UserService() {

	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
		user.setActive(true);
		Roles userRole = roleRepository.findByRole("admin");
		user.setRoles(new HashSet<Roles>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}
}
