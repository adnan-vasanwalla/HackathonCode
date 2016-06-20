package com.fashion.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fashion.model.User;

public interface UserRepository extends MongoRepository<User, String>{

	User findByEmail(String email);
}
