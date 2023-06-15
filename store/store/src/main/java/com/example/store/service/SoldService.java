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
	
	public List<Sold> getSold() {
		return soldRepo.findAll();
	}
	
	public void checkout(String username) {
		jdbcTemplate.execute("SELECT CHECKOUT(?)", (PreparedStatementCallback<Void>) ps -> {
	        ps.setString(1, username);
	        ps.execute();
	        return null;
	    });
	}
}
