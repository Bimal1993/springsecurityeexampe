package com.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.model.Roles;
import com.demo.model.Users;
import com.demo.repo.UserRepository;

@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepo;

//	@Autowired  
//	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		Optional<Users> customer = userRepo.findByEmail(username);
		if (customer.isPresent()) {
			if (bCryptPasswordEncoder().matches(pwd, customer.get().getPassword())) {
//				List<GrantedAuthority> authorities = new ArrayList<>();
//				authorities.add(new SimpleGrantedAuthority(customer.get().getEmail()));
//				return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
				return new UsernamePasswordAuthenticationToken(username, pwd,
						getGrantedAuthorities(customer.get().getRoles()));
			} else {
				throw new BadCredentialsException("Invalid Username or Password!");
			} 
		} else {
			throw new BadCredentialsException("No user registered with this details!");
		} 
	}
 
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
 
	private List<GrantedAuthority> getGrantedAuthorities(Set<Roles> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Roles authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		return grantedAuthorities;
	}

	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
