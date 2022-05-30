//package com.demo.config;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.demo.model.Roles;
//import com.demo.model.Users;
//import com.demo.repo.UserRepository;
//
//@Service
//public class UserDetailsConfig implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userService;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Optional<Users> user = userService.findByEmail(email);
//		if (user.isPresent()) {
//			List<GrantedAuthority> authorities = getUserAuthority(user.get().getRoles());
//			return buildUserForAuthentication(user, authorities);
//		} else { 
//			throw new BadCredentialsException("No user registered with this details!");
//		}
//	}
//
//	private List<GrantedAuthority> getUserAuthority(Set<Roles> userRoles) {
//		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//		for (Roles role : userRoles) {
//			roles.add(new SimpleGrantedAuthority(role.getRole()));
//		}
//		return new ArrayList<>(roles);
//	}
//
//	private UserDetails buildUserForAuthentication(Optional<Users> user, List<GrantedAuthority> authorities) {
//		return new User(user.get().getUserName(), user.get().getPassword(), user.get().getActive(), true, true, true,
//				authorities);
//	}
//
//	@Bean
//	BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}
