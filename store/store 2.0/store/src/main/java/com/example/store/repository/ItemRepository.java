package com.example.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.store.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	public List<Item> findBySellerName(String seller);

	public List<Item> findByCategory(String category);
}
