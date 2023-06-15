package com.example.store.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buyers")
public class Buyer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;
	
	@Column(name = "name")
	@JsonProperty("name")
	private String name;
	
	@Column(name = "email")
	@JsonProperty("email")
	private String email;
	
	@Column(name = "birthday")
	@JsonProperty("birthday")
	private String birthday;
	
	@Column(name = "address")
	@JsonProperty("address")
	private String address;
	
	public Buyer() {
		
	}
	
	public Buyer(String name, String email, String birthday, String address) {
		this.name = name;
		this.email = email;
		this.birthday = birthday;
		this.address = address;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
