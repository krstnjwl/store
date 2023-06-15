package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.store.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsernameOrEmail(String username, String email);
	Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
