package com.fashion.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fashion.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

	
	
}
