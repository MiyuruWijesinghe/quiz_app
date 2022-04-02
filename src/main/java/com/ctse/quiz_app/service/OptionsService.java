package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Options;
import com.ctse.quiz_app.resource.OptionsResource;

@Service
public interface OptionsService {

	public List<Options> findAll();
	
	public Optional<Options> findById(String id);
	
	public List<Options> findByStatus(String status);
	
	public List<Options> findByText(String text);
	
	public List<Options> findByQuestionId(String questionId);
	
	public Options saveOption(String username, OptionsResource optionsResource);
	
	public Options updateOption(String id, String username, OptionsResource optionsResource);
	
	public String deleteOption(String id);
}
