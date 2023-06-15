package com.example.store.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@Column(name = "item_no")
	@JsonProperty("item_no")
	Integer itemNo;
	
	@Column(name = "item_id")
	@JsonProperty("item_id")
	Integer itemId;
	
	@Column(name = "item_name")
	@JsonProperty("item_name")
	String itemName;
	
	@Column(name = "price")
	@JsonProperty("price")
	Integer price;
	
	@Column(name = "amount")
	@JsonProperty("amount")
	Integer amount;
	
	@Column(name = "item_cost")
	@JsonProperty("item_cost")
	Integer itemCost;
	
	
	public Cart() {
		
	}
	
	public Cart(Integer itemNo, Integer itemId, String itemName, Integer price, Integer amount, Integer itemCost) {
		this.itemNo = itemNo;
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.amount = amount;
		this.itemCost = itemCost;
	}
	
	public Integer getItemNo() {
		return itemNo;
	}
	
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	
	public Integer getItemId() {
		return itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getItemCost() {
		return itemCost;
	}
	
	public void setItemCost(Integer itemCost) {
		this.itemCost = itemCost;
	}
}
