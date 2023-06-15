package com.example.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.model.Sold;
import com.example.store.service.SoldService;

@RestController
public class SoldController {
	@Autowired
	SoldService soldService;
	
	@GetMapping("/sold")
	public ResponseEntity<List<Sold>> getSold() {
		return ResponseEntity.ok(soldService.getSold());
	}
	
	@PostMapping("/checkout/{username}")
	public ResponseEntity<Void> checkout(@PathVariable String username) {
		soldService.checkout(username);
		return ResponseEntity.ok(null);
	}
}
