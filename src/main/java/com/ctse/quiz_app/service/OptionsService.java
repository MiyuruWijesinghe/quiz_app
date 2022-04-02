package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Options;
import com.ctse.quiz_app.resource.OptionsResource;

/**
 * Options Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface OptionsService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Options> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Options> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Options> findByStatus(String status);
	
	
	/**
	 * Find by text.
	 *
	 * @param text - the text
	 * @return the list
	 */
	public List<Options> findByText(String text);
	
	
	/**
	 * Find by question id.
	 *
	 * @param questionId - the question id
	 * @return the list
	 */
	public List<Options> findByQuestionId(String questionId);
	
	
	/**
	 * Save option.
	 *
	 * @param username - the username
	 * @param optionsResource - the options resource
	 * @return the options
	 */
	public Options saveOption(String username, OptionsResource optionsResource);
	
	
	/**
	 * Update option.
	 *
	 * @param id - the id
	 * @param username - the username
	 * @param optionsResource - the options resource
	 * @return the options
	 */
	public Options updateOption(String id, String username, OptionsResource optionsResource);
	
	
	/**
	 * Delete option.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deleteOption(String id);
	
}
