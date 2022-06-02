package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.JwtTokenUtil;
import com.demo.config.UserDetailsService;
import com.demo.model.User;
import com.demo.repo.UsersRepository;
import com.demo.service.UserService;

@RestController
public class JwtController {
	@Autowired
	UserService userService;

	@Autowired
	UsersRepository userRepo;

	@Autowired
	private UserDetailsService authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/index")
	public String index() {
		return "Index Page";
	}

	@GetMapping("/emp")
	public String getEmployeePage() {
		return "empPage";
	}

	@GetMapping("/mgr")
	public String getManagerPage() {
		return "mgrPage";
	}

	@GetMapping("/common")
	public String getCommonPage() {
		return "commonPage";
	}

	@GetMapping("/admin")
	public String adminPagee() {
		return "Admin Page";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userService.findUserByEmail(authenticationRequest.getEmail()); // loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(jwt);
	}

	@PostMapping(value = "/registration")
	public String createNewUser(@RequestBody User user) {
		User userExists = userRepo.findByEmail(user.getEmail());
		if (userExists != null) {
			return "Registration failed -- Duplicate User";
		} else {
			userService.saveUser(user);
		}
		return "User Saved";
	}
}
