package com.sheepcow.voting.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sheepcow.voting.models.Users;

public interface UserRepository extends MongoRepository<Users, String> {
	public List<Users> findUserByUsername(String username);
}
