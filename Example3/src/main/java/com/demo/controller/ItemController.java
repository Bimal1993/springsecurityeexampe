package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.model.User;
import com.demo.service.UserService;

@Controller
public class ItemController {
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String getHomePage() {
		return "login";  
	}

	@GetMapping("/index") 
	public String getWelcomePage() {
		return "index";  
	} 
	 
	@GetMapping("/home") 
	public String homePage() {
		return "homePage";  
	} 

	@GetMapping(value = "/registration")
	public String registration(Model m) {
		User user = new User();
		m.addAttribute("user", user);
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String createNewUser(@Validated User user, BindingResult bindingResult, Model model) {
		Optional<User> userExists = userService.findUserByEmail(user.getEmail());
		if (userExists.isPresent()) {
			model.addAttribute("successMessage", "There is already a user registered with the user name provided");
			model.addAttribute("user", new User()); 
			return "registration";
		} else {
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());
		}
		return "registration"; 
	}

	@GetMapping("/admin")
	public String getAdminPage() {
		return "adminPage";
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

	@GetMapping("/access-denied")
	public String getAccessDenied() {
		return "accessdenied";
	}
}
