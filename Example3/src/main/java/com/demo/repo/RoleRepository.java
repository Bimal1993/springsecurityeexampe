package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Roles;

public interface RoleRepository  extends JpaRepository<Roles, Integer> {
	Roles findByRole(String role);
}
