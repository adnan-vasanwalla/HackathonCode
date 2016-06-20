package com.fashion.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fashion.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
	
	List<Product> findByCategory(String category);
	//List<Product> findByBrand(String category, String brand);
	List<Product> findByBrandIn(List<String> brands);
	
	//@Query(value="{category:?0, 'size.?1': {$gt:0}, color:?2, cost }")
	//List<Product> findByCategoryAndSizeAndColorAndCostAndBrand(String category, String size, String color, String cost, String brand);
	//use @query annotation for may
	@Query(value="{'size.?0':{$gt:0}}")
	List<Product> findBySize(String size); 
	
	List<Product> findByColorIn(List<String> color);
	
	List<Product> findByCategoryAndColorIn(String category, List<String> colors);
	List<Product> findByCategoryAndPriceBetween(String category, Double price1, Double price2);
	List<Product> findByCategoryAndBrandIn(String category, List<String> brands);
	List<Product> findByCategoryAndBrandInAndPriceBetween(String category, List<String> brand, Double price1, Double price2);
	List<Product> findByCategoryAndBrandInAndColorIn(String category, List<String> brand, List<String> color);
	List<Product> findByCategoryAndColorInAndPriceBetween(String category, List<String> colors, Double price1, Double price2);

	Long countByCategoryAndPriceBetween(String category, Double l, Double u);
}
