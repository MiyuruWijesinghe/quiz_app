package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Users;
import com.ctse.quiz_app.resource.UsersResource;

/**
 * Users Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface UsersService {
	

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Users> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Users> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Users> findByStatus(String status);
	
	
	/**
	 * Find by user id.
	 *
	 * @param userId - the user id
	 * @return the optional
	 */
	public Optional<Users> findByUserId(String userId);
	
	
	/**
	 * Save user.
	 *
	 * @param usersResource - the users resource
	 * @return the users
	 */
	public Users saveUser(UsersResource usersResource);
	
	
	/**
	 * Update user.
	 *
	 * @param id - the id
	 * @param usersResource - the users resource
	 * @return the users
	 */
	public Users updateUser(String id, UsersResource usersResource);
	
	
	/**
	 * Delete user.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteUser(String id);
	
}
