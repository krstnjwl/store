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

    public List<Cart> getCart(String buyer) {
        return cartRepo.findByBuyerName(buyer);
    }

    public void addToCart(String buyer, Integer itemId, Integer amount) {
        jdbcTemplate.execute("SELECT ADDTOCART(?,?,?)", (PreparedStatementCallback<Void>) ps -> {
            ps.setString(1, buyer);
            ps.setInt(2, itemId);
            ps.setInt(3, amount);
            ps.execute();
            return null;
        });
    }

    public void removeFromCart(Integer itemNo) {
        cartRepo.deleteById(itemNo);
    }

    public void removeAllFromCart(String buyer) {
        cartRepo.deleteByBuyerName(buyer);
    }
}
