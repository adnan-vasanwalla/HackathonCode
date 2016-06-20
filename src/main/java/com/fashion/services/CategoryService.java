package com.fashion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.managers.CategoriesManager;
import com.fashion.model.Category;

@RestController
@ComponentScan(basePackages="com.fashion.managers")
public class CategoryService {

	@Autowired
	private CategoriesManager manager;
	
	@RequestMapping(value="/api/category", method=RequestMethod.GET)
	private List<Category> getAllCategories() {
		return manager.getAllCategories();
	}
	
	@RequestMapping(value="/api/category/{id}", method=RequestMethod.GET)
	private Category getCategory(@PathVariable String id) {
		return manager.getCategoryByID(id);
	}
	
}
