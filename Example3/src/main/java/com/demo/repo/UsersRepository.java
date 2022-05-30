package com.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
