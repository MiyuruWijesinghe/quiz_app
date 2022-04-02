package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ctse.quiz_app.model.Question;
import com.ctse.quiz_app.resource.QuestionResource;

@Service
public interface QuestionService {

	public List<Question> findAll();
	
	public Optional<Question> findById(String id);
	
	public List<Question> findByStatus(String status);
	
	public List<Question> findByText(String text);
	
	public List<Question> findByQuizId(String quizId);
	
	public Question saveQuestion(String username, QuestionResource questionResource);
	
	public Question updateQuestion(String id, String username, QuestionResource questionResource);
	
	public String deleteQuestion(String id);
}
