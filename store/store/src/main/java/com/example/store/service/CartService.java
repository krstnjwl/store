package com.example.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.example.store.model.Cart;
import com.example.store.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Cart> getCart() {
		return cartRepo.findAll();
	}
	
	public void addToCart(Integer itemId, Integer amount) {
		jdbcTemplate.execute("SELECT ADDTOCART(?,?)", (PreparedStatementCallback<Void>) ps -> {
	        ps.setInt(1, itemId);
	        ps.setInt(2, amount);
	        ps.execute();
	        return null;
	    });
	}

	public void removeFromCart(Integer itemNo) {
		cartRepo.deleteById(itemNo);		
	}
	
	
}
