package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository 
public interface UsersRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	//User findByName(String email);
}
