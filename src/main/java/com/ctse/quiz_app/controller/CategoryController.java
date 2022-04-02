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
import com.ctse.quiz_app.model.Category;
import com.ctse.quiz_app.resource.CategoryResource;
import com.ctse.quiz_app.resource.SuccessAndErrorDetailsResource;
import com.ctse.quiz_app.service.CategoryService;


@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryService categoryService;

	
	/**
	 * Gets the all categorys.
	 *
	 * @return the all categorys
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllCategorys() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Category> category = categoryService.findAll();
		if (!category.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>) category, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the category by id.
	 *
	 * @param id - the id
	 * @return the category by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Category> isPresentCategory = categoryService.findById(id);
		if (isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the categorys by status.
	 *
	 * @param status - the status
	 * @return the categorys by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getCategorysByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Category> category = categoryService.findByStatus(status);
		if (!category.isEmpty()) {
			return new ResponseEntity<>((Collection<Category>) category, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the categorys by name.
	 *
	 * @param name - the name
	 * @return the categorys by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getCategorysByName(@PathVariable(value = "name", required = true) String name) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Category> isPresentCategory = categoryService.findByName(name);
		if (isPresentCategory.isPresent()) {
			return new ResponseEntity<>(isPresentCategory, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the category.
	 *
	 * @param categoryResource - the category resource
	 * @return the response entity
	 */
	@PostMapping(value = "/{username}/save")
	public ResponseEntity<Object> addCategory(@PathVariable(value = "username", required = true) String username,
											  @Valid @RequestBody CategoryResource categoryResource) {
		Category category = categoryService.saveCategory(username, categoryResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), category);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update category.
	 *
	 * @param id - the id
	 * @param categoryResource - the category resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable(value = "username", required = true) String username,
												 @PathVariable(value = "id", required = true) String id,
												 @Valid @RequestBody CategoryResource categoryResource) {
		Category category = categoryService.updateCategory(id, username, categoryResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), category);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete category.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id", required = true) String id) {
		String message = categoryService.deleteCategory(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
}
