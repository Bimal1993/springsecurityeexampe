package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.Users;
import com.demo.service.UserService;

//section6
@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/index")
	public String home() {
		return "index";
	}

	@GetMapping("/user")
	public String user() {
		return ("<h2>Welcome user<h2>");
	}

	@GetMapping("/admin")
	public String admin() {
		return ("<h2>Welcome admin<h2>");
	}

	@GetMapping(value = "/registration")
	public String registration(Model m) {
		Users user = new Users();
		m.addAttribute("user", user);
		return "registration";
	}

	@PostMapping(value = "/registration")
	public String createNewUser(@Validated Users user, BindingResult bindingResult, Model model) {
		Optional<Users> userExists = userService.findUserByEmail(user.getEmail());
		if (userExists.isPresent()) {
			model.addAttribute("successMessage", "There is already a user registered with the user name provided");
			model.addAttribute("user", new Users());
			return "registration";
		} else {
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new Users());
		}
		return "registration";
	}
 
	@GetMapping("/access-denied")
	public String getAccessDenied() {
		return "accessdenied";
	}
}
