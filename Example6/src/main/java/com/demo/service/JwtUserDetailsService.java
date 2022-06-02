package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.config.UserDetailsImpl;
import com.demo.model.User;
import com.demo.repo.UsersRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
 
	@Autowired
	UsersRepository userRepo; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User optionalUser = userRepo.findByEmail(username);  
		if (optionalUser == null) { 
			throw new UsernameNotFoundException("User Not found");
		} else {  
			return new UserDetailsImpl(optionalUser);
		} 
	}

}
