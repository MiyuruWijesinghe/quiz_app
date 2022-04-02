package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ctse.quiz_app.model.Options;

/**
 * Options Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface OptionsRepository extends MongoRepository<Options, String> {

	public Optional<Options> findById(String id);
	
	public List<Options> findByStatus(String status);
	
	public List<Options> findByTextContains(String text);

	public Optional<Options> findByIdAndStatus(String id, String status);
	
	public List<Options> findByQuestionsId(String questionId);
	
	public Boolean existsByQuestionsIdAndText(String questionsId, String text);

	public Boolean existsByQuestionsIdAndTextAndIdNotIn(String questionsId, String text, String id);
	
	public Boolean existsByQuestionsIdAndCode(String questionsId, String code);

	public Boolean existsByQuestionsIdAndCodeAndIdNotIn(String questionsId, String code, String id);
	
	public void deleteById(String id);
}
