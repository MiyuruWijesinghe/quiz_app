package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Quiz;
import com.ctse.quiz_app.resource.QuizResource;

@Service
public interface QuizService {

	public List<Quiz> findAll();
	
	public Optional<Quiz> findById(String id);
	
	public List<Quiz> findByStatus(String status);
	
	public List<Quiz> findByName(String name);
	
	public List<Quiz> findByCategoryId(String categoryId);
	
	public Quiz saveQuiz(String username, QuizResource quizResource);
	
	public Quiz updateQuiz(String id, String username, QuizResource quizResource);
	
	public String deleteQuiz(String id);
}
