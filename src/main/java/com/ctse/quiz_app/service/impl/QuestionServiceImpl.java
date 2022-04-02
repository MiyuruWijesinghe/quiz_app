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
import com.ctse.quiz_app.enums.EnableStatus;
import com.ctse.quiz_app.exception.NoRecordFoundException;
import com.ctse.quiz_app.exception.ValidateRecordException;
import com.ctse.quiz_app.model.Question;
import com.ctse.quiz_app.model.Quiz;
import com.ctse.quiz_app.repository.QuestionRepository;
import com.ctse.quiz_app.repository.QuizRepository;
import com.ctse.quiz_app.resource.QuestionResource;
import com.ctse.quiz_app.service.QuestionService;

/**
 * Questions Service Implementation
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
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	public Optional<Question> findById(String id) {
		Optional<Question> question = questionRepository.findById(id);
		if (question.isPresent()) {
			return Optional.ofNullable(question.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Question> findByStatus(String status) {
		return questionRepository.findByStatus(status);
	}

	@Override
	public List<Question> findByText(String text) {
		return questionRepository.findByTextContains(text);
	}

	@Override
	public List<Question> findByQuizId(String quizId) {
		return questionRepository.findByQuizsId(quizId);
	}

	@Override
	public Question saveQuestion(String username, QuestionResource questionResource) {
		Question question = new Question();
		
		if (questionRepository.existsByQuizsIdAndText(questionResource.getQuizId(), questionResource.getText())) {
			throw new ValidateRecordException(environment.getProperty("question.duplicate"), "message");
		}
		
		Optional<Quiz> quiz = quizRepository.findByIdAndStatus(questionResource.getQuizId(), CommonStatus.ACTIVE.toString());
		if (!quiz.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("quiz.invalid-value"), "message");
		} else {
			question.setQuizs(quiz.get());
		}
		
        question.setText(questionResource.getText());
        question.setSolution(questionResource.getSolution());
        if (questionResource.getIsLocked().equalsIgnoreCase(EnableStatus.YES.toString())) {
        	question.setIsLocked(Boolean.TRUE);
        } else {
        	question.setIsLocked(Boolean.FALSE);
        }
        question.setStatus(questionResource.getStatus());
        question.setCreatedUser(username);
        question.setCreatedDate(formatDate(new Date()));
        questionRepository.save(question);
		return question;
	}

	@Override
	public Question updateQuestion(String id, String username, QuestionResource questionResource) {
		Optional<Question> isPresentQuestion = questionRepository.findById(id);
		if (!isPresentQuestion.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (questionRepository.existsByQuizsIdAndTextAndIdNotIn(questionResource.getQuizId(), questionResource.getText(), id)) {
			throw new ValidateRecordException(environment.getProperty("question.duplicate"), "message");
		}
			
		Question question = isPresentQuestion.get();
		
		Optional<Quiz> quiz = quizRepository.findByIdAndStatus(questionResource.getQuizId(), CommonStatus.ACTIVE.toString());
		if (!quiz.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("quiz.invalid-value"), "message");
		} else {
			question.setQuizs(quiz.get());
		}
		
		question.setText(questionResource.getText());
        question.setSolution(questionResource.getSolution());
        if (questionResource.getIsLocked().equalsIgnoreCase(EnableStatus.YES.toString())) {
        	question.setIsLocked(Boolean.TRUE);
        } else {
        	question.setIsLocked(Boolean.FALSE);
        }
		question.setStatus(questionResource.getStatus());
		question.setModifiedUser(username);
		question.setModifiedDate(formatDate(new Date()));
		questionRepository.save(question);
		return question;
	}

	@Override
	public String deleteQuestion(String id) {
		Optional<Question> isPresentQuestion = questionRepository.findById(id);
		if (!isPresentQuestion.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		questionRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
