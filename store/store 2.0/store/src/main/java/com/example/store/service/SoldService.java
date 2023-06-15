package com.example.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.example.store.model.Sold;
import com.example.store.repository.SoldRepository;

@Service
public class SoldService {
	@Autowired
	SoldRepository soldRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Sold> getSold(String seller) {
		return soldRepo.findBySellerName(seller);
	}
	
	public List<Sold> getPurchaseHistory(String buyer) {
		return soldRepo.findByBuyerName(buyer);
	}
	
	public void checkout(String username, String shippingAddress, String paymentMode) {
		jdbcTemplate.execute("SELECT CHECKOUT(?,?,?)", (PreparedStatementCallback<Void>) ps -> {
	        ps.setString(1, username);
	        ps.setString(2, shippingAddress);
	        ps.setString(3, paymentMode);
	        ps.execute();
	        return null;
	    });
	}
}
