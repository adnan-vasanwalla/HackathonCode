package com.fashion.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="product")
public class Product {
	@Id
	private String id;
	private String brand;
	private String category;
	private String color;
	private Date createdDate;
	private ArrayList<String> image;
	private String name;
	private Double price;
	private Long purchased;
	private String seller;
	private Map<String, Integer> size;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public ArrayList<String> getImage() {
		return image;
	}
	public void setImage(ArrayList<String> image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getPurchased() {
		return purchased;
	}
	public void setPurchased(Long purchased) {
		this.purchased = purchased;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public Map<String, Integer> getSize() {
		return size;
	}
	public void setSize(Map<String, Integer> size) {
		this.size = size;
	}
}
