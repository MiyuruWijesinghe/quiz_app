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

import com.ctse.quiz_app.model.Users;
import com.ctse.quiz_app.resource.SuccessAndErrorDetailsResource;
import com.ctse.quiz_app.resource.UsersResource;
import com.ctse.quiz_app.service.UsersService;

/**
 * Users Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")
public class UsersController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private UsersService usersService;

	
	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllUsers() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Users> users = usersService.findAll();
		if (!users.isEmpty()) {
			return new ResponseEntity<>((Collection<Users>) users, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the user by id.
	 *
	 * @param id - the id
	 * @return the user by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Users> isPresentUsers = usersService.findById(id);
		if (isPresentUsers.isPresent()) {
			return new ResponseEntity<>(isPresentUsers, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the users by status.
	 *
	 * @param status - the status
	 * @return the users by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getUsersByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Users> users = usersService.findByStatus(status);
		if (!users.isEmpty()) {
			return new ResponseEntity<>((Collection<Users>) users, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the user by user id.
	 *
	 * @param userId - the user id
	 * @return the user by user id
	 */
	@GetMapping(value = "/userId/{userId}")
	public ResponseEntity<Object> getUserByUserId(@PathVariable(value = "userId", required = true) String userId) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Users> isPresentUser = usersService.findByUserId(userId);
		if (isPresentUser.isPresent()) {
			return new ResponseEntity<>(isPresentUser, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the user.
	 *
	 * @param usersResource - the users resource
	 * @return the response entity
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Object> addUser(@Valid @RequestBody UsersResource usersResource) {
		Users users = usersService.saveUser(usersResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), users);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update user.
	 *
	 * @param id - the id
	 * @param usersResource - the users resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id", required = true) String id,
										     @Valid @RequestBody UsersResource usersResource) {
		Users users = usersService.updateUser(id, usersResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), users);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete user.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id", required = true) String id) {
		String message = usersService.deleteUser(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}
