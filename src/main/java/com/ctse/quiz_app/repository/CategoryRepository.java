package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ctse.quiz_app.model.Category;

/**
 * Category Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

	public Optional<Category> findById(String id);
	
	public List<Category> findByStatus(String status);

	public Optional<Category> findByName(String name);

	public Optional<Category> findByIdAndStatus(String id, String name);

	public Boolean existsByName(String name);

	public Boolean existsByNameAndIdNotIn(String name, String id);
	
	public void deleteById(String id);
}
