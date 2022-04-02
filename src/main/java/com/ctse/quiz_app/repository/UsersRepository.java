package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ctse.quiz_app.model.Users;

/**
 * Users Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {

	public List<Users> findByStatus(String status);
	
	public Optional<Users> findByUserId(String userId);

	public Boolean existsByUserId(String userId);

	public Boolean existsByUserIdAndIdNotIn(String userId, String id);
	
}
