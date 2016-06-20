package com.fashion.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fashion.model.Category;
import com.fashion.repositories.CategoryRepository;

@Component
public class CategoriesManager {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public Category getCategoryByID(String id) {
		return categoryRepository.findOne(id);
	}
	
}
