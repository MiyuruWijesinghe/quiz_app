package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Category;
import com.ctse.quiz_app.resource.CategoryResource;

/**
 * Category Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface CategoryService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Category> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Category> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Category> findByStatus(String status);
	
	
	/**
	 * Find by name.
	 *
	 * @param name - the name
	 * @return the optional
	 */
	public Optional<Category> findByName(String name);
	
	
	/**
	 * Save category.
	 *
	 * @param username - the username
	 * @param categoryResource - the category resource
	 * @return the category
	 */
	public Category saveCategory(String username, CategoryResource categoryResource);
	
	
	/**
	 * Update category.
	 *
	 * @param id - the id
	 * @param username - the username
	 * @param categoryResource - the category resource
	 * @return the category
	 */
	public Category updateCategory(String id, String username, CategoryResource categoryResource);
	
	
	/**
	 * Delete category.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteCategory(String id);
	
}
