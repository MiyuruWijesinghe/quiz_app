package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Quiz;
import com.ctse.quiz_app.resource.QuizResource;

/**
 * Quiz Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface QuizService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Quiz> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Quiz> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Quiz> findByStatus(String status);
	
	
	/**
	 * Find by name.
	 *
	 * @param name - the name
	 * @return the list
	 */
	public List<Quiz> findByName(String name);
	
	
	/**
	 * Find by category id.
	 *
	 * @param categoryId - the category id
	 * @return the list
	 */
	public List<Quiz> findByCategoryId(String categoryId);
	
	
	/**
	 * Save quiz.
	 *
	 * @param username - the username
	 * @param quizResource - the quiz resource
	 * @return the quiz
	 */
	public Quiz saveQuiz(String username, QuizResource quizResource);
	
	
	/**
	 * Update quiz.
	 *
	 * @param id - the id
	 * @param username - the username
	 * @param quizResource - the quiz resource
	 * @return the quiz
	 */
	public Quiz updateQuiz(String id, String username, QuizResource quizResource);
	
	
	/**
	 * Delete quiz.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteQuiz(String id);
	
}
