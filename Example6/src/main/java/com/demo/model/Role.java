package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String role;

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}

}
