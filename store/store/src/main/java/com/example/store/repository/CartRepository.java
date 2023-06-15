package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.store.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
