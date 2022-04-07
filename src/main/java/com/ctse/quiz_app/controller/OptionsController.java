package com.ctse.quiz_app.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctse.quiz_app.model.Options;
import com.ctse.quiz_app.resource.OptionsResource;
import com.ctse.quiz_app.resource.SuccessAndErrorDetailsResource;
import com.ctse.quiz_app.service.OptionsService;

/**
 * Options Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/options")
@CrossOrigin(origins = "*")
public class OptionsController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private OptionsService optionsService;
	
	
	/**
	 * Gets the all options.
	 *
	 * @return the all options
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllOptions() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Options> options = optionsService.findAll();
		if (!options.isEmpty()) {
			return new ResponseEntity<>((Collection<Options>) options, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the option by id.
	 *
	 * @param id - the id
	 * @return the option by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getOptionById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Options> isPresentOption = optionsService.findById(id);
		if (isPresentOption.isPresent()) {
			return new ResponseEntity<>(isPresentOption, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the options by status.
	 *
	 * @param status - the status
	 * @return the options by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getOptionsByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Options> options = optionsService.findByStatus(status);
		if (!options.isEmpty()) {
			return new ResponseEntity<>((Collection<Options>) options, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the options by name.
	 *
	 * @param name - the name
	 * @return the options by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getOptionsByText(@PathVariable(value = "name", required = true) String name) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Options> options = optionsService.findByName(name);
		if (!options.isEmpty()) {
			return new ResponseEntity<>((Collection<Options>) options, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the options by question id.
	 *
	 * @param questionId - the question id
	 * @return the options by question id
	 */
	@GetMapping(value = "/question/{questionId}")
	public ResponseEntity<Object> getOptionsByQuestionId(@PathVariable(value = "questionId", required = true) String questionId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Options> options = optionsService.findByQuestionId(questionId);
		if (!options.isEmpty()) {
			return new ResponseEntity<>((Collection<Options>) options, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the option.
	 *
	 * @param username - the username
	 * @param optionsResource - the options resource
	 * @return the response entity
	 */
	@PostMapping(value = "/{username}/save")
	public ResponseEntity<Object> addOption(@PathVariable(value = "username", required = true) String username,
											@Valid @RequestBody OptionsResource optionsResource) {
		Options options = optionsService.saveOption(username, optionsResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), options);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update option.
	 *
	 * @param username - the username
	 * @param id - the id
	 * @param optionsResource - the options resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updateOption(@PathVariable(value = "username", required = true) String username,
											   @PathVariable(value = "id", required = true) String id,
											   @Valid @RequestBody OptionsResource optionsResource) {
		Options options = optionsService.updateOption(id, username, optionsResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), options);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete option.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteOption(@PathVariable(value = "id", required = true) String id) {
		String message = optionsService.deleteOption(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}
