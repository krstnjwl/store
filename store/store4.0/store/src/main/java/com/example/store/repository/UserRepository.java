package com.example.store.repository;

import com.example.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

	User findByEmail(String email);
}
