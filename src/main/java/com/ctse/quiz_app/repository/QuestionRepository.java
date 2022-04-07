package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ctse.quiz_app.model.Question;

/**
 * Questions Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

	public Optional<Question> findById(String id);
	
	public List<Question> findByStatus(String status);

	public List<Question> findByNameContains(String name);

	public Optional<Question> findByIdAndStatus(String id, String name);
	
	public List<Question> findByQuizsId(String quizsId);

	public Boolean existsByQuizsIdAndName(String quizsId, String name);

	public Boolean existsByQuizsIdAndNameAndIdNotIn(String quizsId, String name, String id);
	
	public void deleteById(String id);
}
