package com.demo.model;

public class Item {
	private Integer id;
	private String name;
	private String category;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public Item(Integer id, String name, String category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
