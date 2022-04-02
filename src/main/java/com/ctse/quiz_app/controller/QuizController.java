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
import com.ctse.quiz_app.model.Quiz;
import com.ctse.quiz_app.resource.QuizResource;
import com.ctse.quiz_app.resource.SuccessAndErrorDetailsResource;
import com.ctse.quiz_app.service.QuizService;


@RestController
@RequestMapping(value = "/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private QuizService quizService;

	
	/**
	 * Gets the all quizes.
	 *
	 * @return the all quizes
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllQuizes() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Quiz> quiz = quizService.findAll();
		if (!quiz.isEmpty()) {
			return new ResponseEntity<>((Collection<Quiz>) quiz, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the quiz by id.
	 *
	 * @param id - the id
	 * @return the quiz by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getQuizById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Quiz> isPresentQuiz = quizService.findById(id);
		if (isPresentQuiz.isPresent()) {
			return new ResponseEntity<>(isPresentQuiz, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the quizes by status.
	 *
	 * @param status - the status
	 * @return the quizes by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getQuizesByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Quiz> quizes = quizService.findByStatus(status);
		if (!quizes.isEmpty()) {
			return new ResponseEntity<>((Collection<Quiz>) quizes, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the quiz by name.
	 *
	 * @param name - the name
	 * @return the quiz by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getQuizByName(@PathVariable(value = "name", required = true) String name) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Quiz> quizes = quizService.findByName(name);
		if (!quizes.isEmpty()) {
			return new ResponseEntity<>((Collection<Quiz>) quizes, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the quizes by category id.
	 *
	 * @param categoryId - the category id
	 * @return the quizes by category id
	 */
	@GetMapping(value = "/category/{categoryId}")
	public ResponseEntity<Object> getQuizesByCategoryId(@PathVariable(value = "categoryId", required = true) String categoryId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Quiz> quizes = quizService.findByCategoryId(categoryId);
		if (!quizes.isEmpty()) {
			return new ResponseEntity<>((Collection<Quiz>) quizes, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the quiz.
	 *
	 * @param username - the username
	 * @param quizResource - the quiz resource
	 * @return the response entity
	 */
	@PostMapping(value = "/{username}/save")
	public ResponseEntity<Object> addQuiz(@PathVariable(value = "username", required = true) String username,
									      @Valid @RequestBody QuizResource quizResource) {
		Quiz quiz = quizService.saveQuiz(username, quizResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), quiz);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update quiz.
	 *
	 * @param username - the username
	 * @param id - the id
	 * @param quizResource - the quiz resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updateQuiz(@PathVariable(value = "username", required = true) String username,
										     @PathVariable(value = "id", required = true) String id,
										     @Valid @RequestBody QuizResource quizResource) {
		Quiz quiz = quizService.updateQuiz(id, username, quizResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), quiz);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete quiz.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteQuiz(@PathVariable(value = "id", required = true) String id) {
		String message = quizService.deleteQuiz(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}

}