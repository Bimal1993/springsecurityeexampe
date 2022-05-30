package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Roles;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

	 Roles findByRole(String role);   
}
