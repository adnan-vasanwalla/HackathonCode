package com.fashion.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.fashion.model.Product;
import com.fashion.repositories.ProductRepository;

@Component
public class ProductManager {

	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private MongoTemplate template;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> getProductByCategory(String category) {
		return productRepository.findByCategory(category);
	}
	
	public List<Product> getProductByBrand(List<String> brands) {
		return productRepository.findByBrandIn(brands);
	}
	
	public List<Product> getProductBySize(String size) {
		return productRepository.findBySize(size);
	}
	
	public List<Product> getProductByColor(List<String> colors) {
		return productRepository.findByColorIn(colors);
	}
	
	public List<Product> getProductByCategoryAndColor(String category, List<String> colors) {
		return productRepository.findByCategoryAndColorIn(category, colors);
	}
	
	public List<Product> getProductByCategoryAndPrice(String category, Double p1, Double p2) {
		return productRepository.findByCategoryAndPriceBetween(category, p1, p2);
	}
	
	public List<Product> getProductByCategoryAndBrand(String category, List<String> brands) {
		return productRepository.findByCategoryAndBrandIn(category, brands);
	}
	
	public List<Product> getProductByCategoryAndBrandAndPrice(String category, List<String> brands, Double price) {
		return productRepository.findByCategoryAndBrandInAndPriceBetween(category, brands, price-100, price+100);
	}
	
	public List<Product> getProductByCategoryAndBrandAndSize(String category, List<String> brands, String size) {
		return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category).and("brand").in(brands)), Product.class);
	}
	
	public List<Product> getProductByCategoryAndBrandAndColor(String category, List<String> brands, List<String> colors) {
		return productRepository.findByCategoryAndBrandInAndColorIn(category, brands, colors);
	}
	
	public List<Product> getProductByCategoryAndPriceAndSize(String category, Double price, String size) {
		return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category).and("price").lte(price+100).andOperator(Criteria.where("price").gte(price-100))), Product.class);
	}
	
	public List<Product> getProductByCategoryAndPriceAndColor(String category, Double price, List<String> colors) {
		return productRepository.findByCategoryAndColorInAndPriceBetween(category, colors, price-100, price+100);
	}
	
	public List<Product> getProductByCategoryAndSizeAndColor(String category, String size, List<String> colors) {
		return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category).and("color").in(colors)), Product.class);
	}
	
	public List<Product> getProductByCategoryAndBrandAndPriceAndSize(String category, List<String> brands, Double price, String size) {
		return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category).and("brand").in(brands).and("price").lte(price+100).andOperator(Criteria.where("price").gte(price-100))), Product.class);
	}
	
	public List<Product> getProductByCategoryAndBrandAndPriceAndColor(String category, List<String> brands, Double price, List<String> colors) {
		return template.find(Query.query(Criteria.where("category").is(category).and("brand").in(brands).and("color").in(colors).and("price").lte(price+100).andOperator(Criteria.where("price").gte(price-100))), Product.class);
	}
	
	public List<Product> getProductByCategoryAndBrandAndSizeAndColor(String category, List<String> brands, String size, List<String> colors) {
		return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category).and("brand").in(brands).and("color").in(colors)), Product.class);
	}
	
	public List<Product> getProductByCategoryAndBrandAndPriceAndSizeAndColor(String category, List<String> brands, Double price, String size, List<String> colors) {
		return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category).and("brand").in(brands).and("color").in(colors).and("price").lte(price+100).andOperator(Criteria.where("price").gte(price-100))), Product.class);
	}
	
	public Product getProduct(String id) {
		return productRepository.findOne(id);
	}
}
