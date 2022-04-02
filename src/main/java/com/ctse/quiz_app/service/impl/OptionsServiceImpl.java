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
import com.ctse.quiz_app.model.Options;
import com.ctse.quiz_app.model.Question;
import com.ctse.quiz_app.repository.OptionsRepository;
import com.ctse.quiz_app.repository.QuestionRepository;
import com.ctse.quiz_app.resource.OptionsResource;
import com.ctse.quiz_app.service.OptionsService;

@Component
@Transactional(rollbackFor=Exception.class)
public class OptionsServiceImpl implements OptionsService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private OptionsRepository optionsRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
	@Override
	public List<Options> findAll() {
		return optionsRepository.findAll();
	}

	@Override
	public Optional<Options> findById(String id) {
		Optional<Options> option = optionsRepository.findById(id);
		if (option.isPresent()) {
			return Optional.ofNullable(option.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Options> findByStatus(String status) {
		return optionsRepository.findByStatus(status);
	}

	@Override
	public List<Options> findByText(String text) {
		return optionsRepository.findByTextContains(text);
	}

	@Override
	public List<Options> findByQuestionId(String questionId) {
		return optionsRepository.findByQuestionsId(questionId);
	}

	@Override
	public Options saveOption(String username, OptionsResource optionsResource) {
		Options options = new Options();
		
		if (optionsRepository.existsByQuestionsIdAndText(optionsResource.getQuestionId(), optionsResource.getText())) {
			throw new ValidateRecordException(environment.getProperty("option.duplicate"), "message");
		}
		
		Optional<Question> question = questionRepository.findByIdAndStatus(optionsResource.getQuestionId(), CommonStatus.ACTIVE.toString());
		if (!question.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("question.invalid-value"), "message");
		} else {
			options.setQuestions(question.get());
		}
		
		options.setCode(optionsResource.getCode());
		options.setText(optionsResource.getText());
        if (optionsResource.getIsCorrect().equalsIgnoreCase(EnableStatus.YES.toString())) {
        	options.setIsCorrect(Boolean.TRUE);
        } else {
        	options.setIsCorrect(Boolean.FALSE);
        }
        options.setStatus(optionsResource.getStatus());
        options.setCreatedUser(username);
        options.setCreatedDate(formatDate(new Date()));
        optionsRepository.save(options);
		return options;
	}

	@Override
	public Options updateOption(String id, String username, OptionsResource optionsResource) {
		Optional<Options> isPresentOption = optionsRepository.findById(id);
		if (!isPresentOption.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (optionsRepository.existsByQuestionsIdAndTextAndIdNotIn(optionsResource.getQuestionId(), optionsResource.getText(), id)) {
			throw new ValidateRecordException(environment.getProperty("option.duplicate"), "message");
		}
			
		Options options = isPresentOption.get();
		
		Optional<Question> question = questionRepository.findByIdAndStatus(optionsResource.getQuestionId(), CommonStatus.ACTIVE.toString());
		if (!question.isPresent()) {
			throw new ValidateRecordException(environment.getProperty("question.invalid-value"), "message");
		} else {
			options.setQuestions(question.get());
		}
		
		options.setCode(optionsResource.getCode());
		options.setText(optionsResource.getText());
        if (optionsResource.getIsCorrect().equalsIgnoreCase(EnableStatus.YES.toString())) {
        	options.setIsCorrect(Boolean.TRUE);
        } else {
        	options.setIsCorrect(Boolean.FALSE);
        }
        options.setStatus(optionsResource.getStatus());
		options.setModifiedUser(username);
		options.setModifiedDate(formatDate(new Date()));
		optionsRepository.save(options);
		return options;
	}

	@Override
	public String deleteOption(String id) {
		Optional<Options> isPresentOption = optionsRepository.findById(id);
		if (!isPresentOption.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		optionsRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
