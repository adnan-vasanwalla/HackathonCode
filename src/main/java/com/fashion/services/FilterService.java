package com.fashion.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.managers.ProductManager;
import com.fashion.model.CostEnum;
import com.fashion.model.Product;
import com.fashion.repositories.ProductRepository;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

@RestController
@ComponentScan(basePackages="com.fashion.managers")
public class FilterService {

	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private MongoTemplate template;
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET)
	private List<Product> getAllProducts() {
		return productManager.getAllProducts();
	}
	
	//@RequestMapping(value="/api/product", method=RequestMethod.GET)
	//Fix this to return filters
	
	@RequestMapping(value="/api/filter", method=RequestMethod.GET)
	private Map<String, List<String>> getProductByCategory(@RequestParam("category")String category) {
		List<Map<String, Integer>> sizeMap = template.getCollection("product").distinct("size", buildDBObject(category, null, null, null, null));
		List<String> colorList = template.getCollection("product").distinct("color", buildDBObject(category, null, null, null, null));
		List<String> brandList = template.getCollection("product").distinct("brand", buildDBObject(category, null, null, null, null));
		List<Integer> costList = template.getCollection("product").distinct("cost", buildDBObject(category, null, null, null, null));
		
		Set<String> keys = new HashSet<String>();
		for(Map<String, Integer> map : sizeMap) {
			keys.addAll(map.keySet());
		}
		
		Map<String, List<String>> toReturn = new HashMap<String, List<String>>();
		toReturn.put("sizes", new ArrayList<String>(keys));
		toReturn.put("colors", colorList);
		toReturn.put("brands", brandList);
		toReturn.put("costs", buildCostList(category));
		//return productManager.getProductByCategory(category);
		return toReturn;
	}
	
	private List<String> buildCostList(String category) {
		List<String> costList = new ArrayList<>();
		if (productRepository.countByCategoryAndPriceBetween(category, 0.0, 1000.0) > 0) {
			costList.add("Less Than 1000");
		} else if (productRepository.countByCategoryAndPriceBetween(category, 1000.0, 2000.0) > 0) {
			costList.add("1000-2000");
		} else if (productRepository.countByCategoryAndPriceBetween(category, 2000.0, 3000.0) > 0) {
			costList.add("2000-3000");
		} else if (productRepository.countByCategoryAndPriceBetween(category, 3000.0, 4000.0) > 0) {
			costList.add("3000-4000");
		} else if (productRepository.countByCategoryAndPriceBetween(category, 4000.0, 5000.0) > 0) {
			costList.add("4000-5000");
		} else {
			costList.add("Greater Than 5000");
		}
		return costList;
	}
	
	private DBObject buildDBObject(String category, List<String> sizes, List<String> colors, 
			List<String> brands, List<String> costs) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start("category", category);
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				builder.append("\"size." + size + "\"" , BasicDBObjectBuilder.start("$ne", null).get());
			}
		}
		if(colors != null && !colors.isEmpty()) {
			for(String color : colors) {
				builder.append("color", color);
			}
		}
		if(brands != null && !brands.isEmpty()) {
			for(String brand : brands) {
				builder.append("brand", brand);
			}
		}
		if(costs != null && !costs.isEmpty()) {
			for(String cost : costs) {
				builder.append("price", CostEnum.getDBObjectFromString(cost));
			}
		}
		return builder.get();
	}
	
	/*@RequestMapping(value="/api/product/category", method=RequestMethod.GET)
	private List<Product> getProductsByCategory(@RequestParam("category") String category) {
		return productManager.getProductByCategory(category);
	}*/
	/*
	@RequestMapping(value="/api/product/brand", method=RequestMethod.GET)
	private List<Product> getProductsByBrand(@RequestParam("brand") String brand) {
		return productManager.getProductByBrand(brand);
	}
	
	@RequestMapping(value="/api/product/size", method=RequestMethod.GET)
	private List<Product> getProductsBySize(@RequestParam("size") String size) {
		List<Product> products = template.find(Query.query(Criteria.where("size." + size).gt(0)), Product.class);
		//return productManager.getProductBySize(size);
		return products;
	}*/
}
