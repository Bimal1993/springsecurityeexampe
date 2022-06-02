package com.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.model.Role;
import com.demo.model.User;
import com.demo.repo.UsersRepository;

@Component
public class UserDetailsService implements AuthenticationProvider {

	@Autowired
	private UsersRepository userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User user = userService.findByEmail(email);
		if (user != null) {
			if (bCryptPasswordEncoder().matches(pwd, user.getPassword())) {
				return new UsernamePasswordAuthenticationToken(email, pwd, getGrantedAuthorities(user.getRole()));
			} else {
				throw new BadCredentialsException("Invalid username or password!");
			}
		} else {
			throw new BadCredentialsException("No user registered with this details!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private List<GrantedAuthority> getGrantedAuthorities(Set<Role> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Role authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		return grantedAuthorities;
	}

}
