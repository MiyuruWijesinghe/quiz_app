package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Category;
import com.ctse.quiz_app.resource.CategoryResource;

@Service
public interface CategoryService {

	public List<Category> findAll();
	
	public Optional<Category> findById(String id);
	
	public List<Category> findByStatus(String status);
	
	public Optional<Category> findByName(String name);
	
	public Category saveCategory(String username, CategoryResource categoryResource);
	
	public Category updateCategory(String id, String username, CategoryResource categoryResource);
	
	public String deleteCategory(String id);
	
}
