package com.ctse.quiz_app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ctse.quiz_app.enums.CommonStatus;
import com.ctse.quiz_app.exception.NoRecordFoundException;
import com.ctse.quiz_app.exception.ValidateRecordException;
import com.ctse.quiz_app.model.Category;
import com.ctse.quiz_app.model.Quiz;
import com.ctse.quiz_app.repository.CategoryRepository;
import com.ctse.quiz_app.repository.QuizRepository;
import com.ctse.quiz_app.resource.QuizResource;
import com.ctse.quiz_app.service.QuizService;

@Component
@Transactional(rollbackFor=Exception.class)
public class QuizServiceImpl implements QuizService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
	@Override
	public List<Quiz> findAll() {
		return quizRepository.findAll();
	}

	@Override
	public Optional<Quiz> findById(String id) {
		Optional<Quiz> quiz = quizRepository.findById(id);
		if (quiz.isPresent()) {
			return Optional.ofNullable(quiz.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Quiz> findByStatus(String status) {
		return quizRepository.findByStatus(status);
	}

	@Override
	public List<Quiz> findByName(String name) {
		return quizRepository.findByNameContains(name);
	}

	@Override
	public List<Quiz> findByCategoryId(String categoryId) {
		return quizRepository.findByCategorysId(categoryId);
	}

	@Override
	public Quiz saveQuiz(String username, QuizResource quizResource) {
		Quiz quiz = new Quiz();
		
		if (quizRepository.existsByCategorysIdAndName(quizResource.getCategoryId(), quizResource.getName())) {
			throw new ValidateRecordException(environment.getProperty("quiz.duplicate"), "message");
		}
		
		Optional<Category> category = categoryRepository.findByIdAndStatus(quizResource.getCategoryId(), CommonStatus.ACTIVE.toString());
		if (!category.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("category.invalid-value"), "message");
		} else {
			quiz.setCategorys(category.get());
		}
		
		quiz.setName(quizResource.getName());
		quiz.setDescription(quizResource.getDescription());
		quiz.setStatus(quizResource.getStatus());
		quiz.setCreatedUser(username);
		quiz.setCreatedDate(formatDate(new Date()));
		quizRepository.save(quiz);
		return quiz;
	}

	@Override
	public Quiz updateQuiz(String id, String username, QuizResource quizResource) {
		Optional<Quiz> isPresentQuiz = quizRepository.findById(id);
		if (!isPresentQuiz.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (quizRepository.existsByCategorysIdAndNameAndIdNotIn(quizResource.getCategoryId(), quizResource.getName(), id)) {
			throw new ValidateRecordException(environment.getProperty("quiz.duplicate"), "message");
		}
			
		Quiz quiz = isPresentQuiz.get();
		
		Optional<Category> category = categoryRepository.findByIdAndStatus(quizResource.getCategoryId(), CommonStatus.ACTIVE.toString());
		if (!category.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("category.invalid-value"), "message");
		} else {
			quiz.setCategorys(category.get());
		}
		
		quiz.setName(quizResource.getName());
		quiz.setDescription(quizResource.getDescription());
		quiz.setStatus(quizResource.getStatus());
		quiz.setModifiedUser(username);
		quiz.setModifiedDate(formatDate(new Date()));
		quizRepository.save(quiz);
		return quiz;
	}

	@Override
	public String deleteQuiz(String id) {
		Optional<Quiz> isPresentQuiz = quizRepository.findById(id);
		if (!isPresentQuiz.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		quizRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

	
}
