package com.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.users.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	public User findByUsername(String username);
}
