package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ctse.quiz_app.model.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {

	public List<Users> findByStatus(String status);
	
	public Optional<Users> findByUserId(String userId);
	
}
