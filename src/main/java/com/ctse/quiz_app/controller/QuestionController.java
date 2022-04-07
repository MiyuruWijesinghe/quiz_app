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

import com.ctse.quiz_app.model.Question;
import com.ctse.quiz_app.resource.QuestionResource;
import com.ctse.quiz_app.resource.SuccessAndErrorDetailsResource;
import com.ctse.quiz_app.service.QuestionService;

/**
 * Questions Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private QuestionService questionService;

	
	/**
	 * Gets the all questions.
	 *
	 * @return the all questions
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllQuestions() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Question> question = questionService.findAll();
		if (!question.isEmpty()) {
			return new ResponseEntity<>((Collection<Question>) question, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the question by id.
	 *
	 * @param id - the id
	 * @return the question by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getQuestionById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Question> isPresentQuestion = questionService.findById(id);
		if (isPresentQuestion.isPresent()) {
			return new ResponseEntity<>(isPresentQuestion, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the questions by status.
	 *
	 * @param status - the status
	 * @return the questions by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getQuestionsByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Question> questions = questionService.findByStatus(status);
		if (!questions.isEmpty()) {
			return new ResponseEntity<>((Collection<Question>) questions, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the questions by name.
	 *
	 * @param name - the name
	 * @return the questions by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getQuestionsByName(@PathVariable(value = "name", required = true) String name) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Question> questions = questionService.findByName(name);
		if (!questions.isEmpty()) {
			return new ResponseEntity<>((Collection<Question>) questions, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the questions by quiz id.
	 *
	 * @param quizId - the quiz id
	 * @return the questions by quiz id
	 */
	@GetMapping(value = "/quiz/{quizId}")
	public ResponseEntity<Object> getQuestionsByQuizId(@PathVariable(value = "quizId", required = true) String quizId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Question> questions = questionService.findByQuizId(quizId);
		if (!questions.isEmpty()) {
			return new ResponseEntity<>((Collection<Question>) questions, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the question.
	 *
	 * @param username - the username
	 * @param questionResource - the question resource
	 * @return the response entity
	 */
	@PostMapping(value = "/{username}/save")
	public ResponseEntity<Object> addQuestion(@PathVariable(value = "username", required = true) String username,
											  @Valid @RequestBody QuestionResource questionResource) {
		Question question = questionService.saveQuestion(username, questionResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), question);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update questions.
	 *
	 * @param username - the username
	 * @param id - the id
	 * @param questionResource - the question resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updateQuestions(@PathVariable(value = "username", required = true) String username,
												  @PathVariable(value = "id", required = true) String id,
												  @Valid @RequestBody QuestionResource questionResource) {
		Question question = questionService.updateQuestion(id, username, questionResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), question);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete question.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteQuestion(@PathVariable(value = "id", required = true) String id) {
		String message = questionService.deleteQuestion(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}

}
