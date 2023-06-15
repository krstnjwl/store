package com.example.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.model.Item;
import com.example.store.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepo;

    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    public List<Item> getSellerItems(String seller) {
        return itemRepo.findBySellerName(seller);
    }
    public Item getItemById(Integer itemId) {
        return itemRepo.findById(itemId)
            .orElse(null);
    }

    public List<Item> getItemsByCategory(String category) {
        return itemRepo.findByCategory(category);
    }

    public Item addItem(Item item) {
        return itemRepo.save(item);
    }

    public void deleteItem(Integer itemId) {
        itemRepo.deleteById(itemId);
    }

    public Item updateItem(Integer itemId, Item updatedItem) {
        Item existingItem = itemRepo.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with ID: " + itemId));

        existingItem.setPicture(updatedItem.getPicture());
        existingItem.setItemName(updatedItem.getItemName());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setSellerName(updatedItem.getSellerName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setStocks(updatedItem.getStocks());

        return itemRepo.save(existingItem);
    }
}
