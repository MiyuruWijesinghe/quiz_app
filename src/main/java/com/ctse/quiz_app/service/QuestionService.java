package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ctse.quiz_app.model.Question;
import com.ctse.quiz_app.resource.QuestionResource;

/**
 * Questions Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface QuestionService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Question> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Question> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Question> findByStatus(String status);
	
	
	/**
	 * Find by name.
	 *
	 * @param name - the name
	 * @return the list
	 */
	public List<Question> findByName(String name);
	
	
	/**
	 * Find by quiz id.
	 *
	 * @param quizId - the quiz id
	 * @return the list
	 */
	public List<Question> findByQuizId(String quizId);
	
	
	/**
	 * Save question.
	 *
	 * @param username - the username
	 * @param questionResource - the question resource
	 * @return the question
	 */
	public Question saveQuestion(String username, QuestionResource questionResource);
	
	
	/**
	 * Update question.
	 *
	 * @param id - the id
	 * @param username - the username
	 * @param questionResource - the question resource
	 * @return the question
	 */
	public Question updateQuestion(String id, String username, QuestionResource questionResource);
	
	
	/**
	 * Delete question.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteQuestion(String id);
	
}
