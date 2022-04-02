package com.ctse.quiz_app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ctse.quiz_app.exception.NoRecordFoundException;
import com.ctse.quiz_app.exception.ValidateRecordException;
import com.ctse.quiz_app.model.Category;
import com.ctse.quiz_app.repository.CategoryRepository;
import com.ctse.quiz_app.resource.CategoryResource;
import com.ctse.quiz_app.service.CategoryService;

/**
 * Category Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(String id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return Optional.ofNullable(category.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Category> findByStatus(String status) {
		return categoryRepository.findByStatus(status);
	}

	@Override
	public Optional<Category> findByName(String name) {
		Optional<Category> category = categoryRepository.findByName(name);
		if (category.isPresent()) {
			return Optional.ofNullable(category.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Category saveCategory(String username, CategoryResource categoryResource) {
		Category category = new Category();
		
        if (categoryRepository.existsByName(categoryResource.getName())) {
        	throw new ValidateRecordException(environment.getProperty("name.duplicate"), "message");
		}
		
        category.setName(categoryResource.getName());
        category.setDescription(categoryResource.getDescription());
        category.setImageURL(categoryResource.getImageURL());
        category.setIcon(categoryResource.getIcon());
        category.setBackgroundColor(categoryResource.getBackgroundColor());
        category.setStatus(categoryResource.getStatus());
        category.setCreatedUser(username);
        category.setCreatedDate(formatDate(new Date()));
        categoryRepository.save(category);
		return category;
	}

	@Override
	public Category updateCategory(String id, String username, CategoryResource categoryResource) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (!isPresentCategory.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (categoryRepository.existsByNameAndIdNotIn(categoryResource.getName(), id)) {
			throw new ValidateRecordException(environment.getProperty("name.duplicate"), "message");
		}
		
		Category category = isPresentCategory.get();
		category.setName(categoryResource.getName());
        category.setDescription(categoryResource.getDescription());
        category.setImageURL(categoryResource.getImageURL());
        category.setIcon(categoryResource.getIcon());
        category.setBackgroundColor(categoryResource.getBackgroundColor());
        category.setStatus(categoryResource.getStatus());
        category.setModifiedUser(username);
        category.setModifiedDate(formatDate(new Date()));
        categoryRepository.save(category);
		return category;
	}

	@Override
	public String deleteCategory(String id) {
		Optional<Category> isPresentCategory = categoryRepository.findById(id);
		if (!isPresentCategory.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		categoryRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}
	
}
