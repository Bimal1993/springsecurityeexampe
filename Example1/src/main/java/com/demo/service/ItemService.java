package com.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.model.Item;

@Service
public class ItemService {
	public static List<Item> items;
	static {
		items = new ArrayList<>(
				Arrays.asList(new Item(1, "Spring Boot in Action", "Books"), new Item(2, "Java 8 in Action", "Books"),
						new Item(3, "Data Structures", "Books"), new Item(4, "Spring Boot Security", "Books")));
	}

	public List<Item> getAllItems() {
		return items;
	}
}
