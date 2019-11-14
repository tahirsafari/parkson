package com.parkson.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parkson.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
