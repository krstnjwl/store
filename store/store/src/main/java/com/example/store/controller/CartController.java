package com.example.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.model.Cart;
import com.example.store.service.CartService;

@RestController
public class CartController {
	@Autowired
	CartService cartService;
	
	@GetMapping("/cart")
	public ResponseEntity<List<Cart>> getCart() {
		return ResponseEntity.ok(cartService.getCart());
	}
	
	@PostMapping("/addToCart/{itemId}/{amount}")
	public ResponseEntity<Void> addToCart(@PathVariable Integer itemId, @PathVariable Integer amount) {
		cartService.addToCart(itemId, amount);
		return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/removeFromCart/{itemNo}") 
	public ResponseEntity<Void> removeFromCart(@PathVariable Integer itemNo) {
		cartService.removeFromCart(itemNo);
		return ResponseEntity.ok(null);
	}
}
