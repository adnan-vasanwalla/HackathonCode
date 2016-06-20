package com.fashion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.managers.ProductManager;
import com.fashion.model.CostEnum;
import com.fashion.model.Product;
import com.mongodb.DBObject;

@RestController
@ComponentScan(basePackages="com.fashion.managers")
public class ProductService {

	@Autowired
	private ProductManager productManager;
	
	@Autowired
	private MongoTemplate template;
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category"})
	private List<Product> getProductByCategory(@RequestParam("category")String category) {
		return productManager.getProductByCategory(category);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "colors"})
	private List<Product> getProductByCategoryAndColor(@RequestParam("category")String category, @RequestParam("colors")List<String> colors) {
		return productManager.getProductByCategoryAndColor(category, colors);
	}
	
	////
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "sizes"})
	private List<Product> getProductByCategoryAndSize(@RequestParam("category")String category, @RequestParam("sizes")List<String> sizes) {
		//return template.find(Query.query(Criteria.where("size." + size).gt(0).and("category").is(category)), Product.class);
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category);
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "price"})
	private List<Product> getProductByCategoryAndPrice(@RequestParam("category")String category, @RequestParam("price")String price) {
		/*template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price))), Product.class)*/;
		return template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price))), Product.class);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands"})
	private List<Product> getProductByCategoryAndBrand(@RequestParam("category")String category, @RequestParam("brands")List<String> brands) {
		return productManager.getProductByCategoryAndBrand(category, brands);
	}
	///////////////////////////////////////////////
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "price"})
	private List<Product> getProductByCategoryAndBrandAndPrice(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("price")String price) {
		/*template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price)).and("brand").in(brands)), Product.class);*/
		//return productManager.getProductByCategoryAndBrandAndPrice(category, brands, Double.parseDouble(price));
		return template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price)).and("brand").in(brands)), Product.class);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "sizes"})
	private List<Product> getProductByCategoryAndBrandAndSize(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("sizes")List<String> sizes) {
		//return productManager.getProductByCategoryAndBrandAndSize(category, brands, size);
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category).and("brand").in(brands);
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "colors"})
	private List<Product> getProductByCategoryAndBrand(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("colors")List<String> colors) {
		return productManager.getProductByCategoryAndBrandAndColor(category, brands, colors);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "price", "sizes"})
	private List<Product> getProductByCategoryAndPriceAndSize(@RequestParam("category")String category, @RequestParam("price")String price, @RequestParam("sizes")List<String> sizes) {
		//return productManager.getProductByCategoryAndPriceAndSize(category, Double.parseDouble(price), size);
		//return template.find(Query.query(Criteria.where("category").is(category).
		//		and("price").is(CostEnum.getDBObjectFromString(price)).and("brand").in(brands)), Product.class);
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category).and("price").is(CostEnum.getDBObjectFromString(price));
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "price", "colors"})
	private List<Product> getProductByCategoryAndPriceAndColor(@RequestParam("category")String category, @RequestParam("price")String price, @RequestParam("colors")List<String> colors) {
		//return productManager.getProductByCategoryAndPriceAndColor(category, Double.parseDouble(price), colors);
		return template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price)).and("color").in(colors)), Product.class);
	}
	
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "sizes", "colors"})
	private List<Product> getProductByCategoryAndSizeAndColor(@RequestParam("category")String category, @RequestParam("sizes")List<String> sizes, @RequestParam("colors")List<String> colors) {
		//return productManager.getProductByCategoryAndSizeAndColor(category, size, colors);
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category).and("color").in(colors);
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "price", "sizes"})
	private List<Product> getProductByCategoryAndBrandAndPriceAndSize(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("price")String price, @RequestParam("sizes")List<String> sizes) {
		//return productManager.getProductByCategoryAndBrandAndPriceAndSize(category, brands, Double.parseDouble(price), size);
		/*return template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price)).and("brand").in(brands)), Product.class)*/
	
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category).and("brand").in(brands).and("price").is(CostEnum.getDBObjectFromString(price));
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "price", "colors"})
	private List<Product> getProductByCategoryAndBrandAndPriceAndColor(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("price")String price, @RequestParam("colors")List<String> colors) {
		//return productManager.getProductByCategoryAndBrandAndPriceAndColor(category, brands, Double.parseDouble(price), colors);
		return template.find(Query.query(Criteria.where("category").is(category).
				and("price").is(CostEnum.getDBObjectFromString(price)).and("brand").in(brands).and("color").in(colors)), Product.class);
	}
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "sizes", "colors"})
	private List<Product> getProductByCategoryAndBrandAndSizeAndColor(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("sizes")List<String> sizes, @RequestParam("colors")List<String> colors) {
		//return productManager.getProductByCategoryAndBrandAndSizeAndColor(category, brands, size, colors);
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category).and("brand").in(brands).and("color").in(colors);
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
	@RequestMapping(value="/api/product", method=RequestMethod.GET, params={"category", "brands", "price", "sizes", "colors"})
	private List<Product> getProductByCategoryAndBrandAndPriceAndSizeAndColor(@RequestParam("category")String category, @RequestParam("brands")List<String> brands, @RequestParam("price")String price, @RequestParam("sizes")List<String> sizes, @RequestParam("colors")List<String> colors) {
		//return productManager.getProductByCategoryAndBrandAndPriceAndSizeAndColor(category, brands, Double.parseDouble(price), size, colors);
		//return template.find(Query.query(Criteria.where("category").is(category).
		//		and("price").is(CostEnum.getDBObjectFromString(price)).and("brand").in(brands)), Product.class)
	
		Criteria criteria = new Criteria();
		criteria = criteria.where("category").is(category).and("brand").in(brands).and("price").is(CostEnum.getDBObjectFromString(price)).and("color").in(colors);
		if(sizes != null && !sizes.isEmpty()) {
			for(String size : sizes) {
				criteria = criteria.and("size." + size).gt(0);
			}
		}
		return template.find(Query.query(criteria), Product.class);
	}
}
