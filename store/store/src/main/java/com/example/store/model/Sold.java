package com.example.store.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sold")
public class Sold {
	@Id
	@Column(name = "transaction_no")
	@JsonProperty("transaction_no")
	Integer transactionNo;

	@Column(name = "buyer_name")
	@JsonProperty("buyer_name")
	String buyerName;
	
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
	
	
	public Sold() {
		
	}
	
	public Sold(Integer transactionNo, String buyerName,Integer itemId, String itemName, Integer price, Integer amount, Integer itemCost) {
		this.transactionNo = transactionNo;
		this.buyerName = buyerName;
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.amount = amount;
		this.itemCost = itemCost;
	}
	
	public Integer getTransactionNo() {
		return transactionNo;
	}
	
	public void setTransactionNo(Integer transactionNo) {
		this.transactionNo = transactionNo;
	}
	
	public String getBuyerName() {
		return buyerName;
	}
	
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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
