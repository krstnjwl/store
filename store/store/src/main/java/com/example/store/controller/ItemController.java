package com.example.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.model.Item;
import com.example.store.service.ItemService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Replace with the correct URL of your React application
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getItems() {
		return ResponseEntity.ok(itemService.getItems());
	}
	
	@PostMapping("/add")
	public ResponseEntity<Void> addItem(@RequestBody Item item) {
		itemService.addItem(item);
        return ResponseEntity.ok(null);
	}
	
	@PutMapping("/edit/{itemId}")
	public ResponseEntity<Void> updateItem(@PathVariable Integer itemId, @RequestBody Item updatedItem) {
	    itemService.updateItem(itemId, updatedItem);
	    return ResponseEntity.ok(null);
	}
	
	@DeleteMapping("/delete/{itemId}") 
	public ResponseEntity<Void> deleteItem(@PathVariable Integer itemId) {
		itemService.deleteItem(itemId);
		return ResponseEntity.ok(null);
	}
}
