package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.model.Item;
import com.demo.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itrmService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/getAllItems")
	@ResponseBody
	public ResponseEntity<List<Item>> getAllItems() {
		List<Item> items = itrmService.getAllItems();
		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@GetMapping("/admin")
	@ResponseBody
	public String helloUser() {
		return "Hello Admin!";
	}

	@GetMapping("/user")
	@ResponseBody
	public String helloAdmin() {
		return "Hello User!";
	}

	@GetMapping("/access-denied")
	@ResponseBody
	public String accessDenied() {
		return "Access Denied";
	}

}
