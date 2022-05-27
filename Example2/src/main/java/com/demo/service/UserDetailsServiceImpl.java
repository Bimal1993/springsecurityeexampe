package com.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.model.Role;
import com.demo.model.User;
import com.demo.repo.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User optionalUser = usersRepository.findByFirstname(username);
		if (optionalUser == null) {
			throw new UsernameNotFoundException("User Not found");
		} else {
			return new UserDetailsImpl(optionalUser);
		}
	}
	
}
