package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ctse.quiz_app.model.Question;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

	public Optional<Question> findById(String id);
	
	public List<Question> findByStatus(String status);

	public List<Question> findByTextContains(String text);

	public Optional<Question> findByIdAndStatus(String id, String name);
	
	public List<Question> findByQuizsId(String quizsId);

	public Boolean existsByQuizsIdAndText(String quizsId, String text);

	public Boolean existsByQuizsIdAndTextAndIdNotIn(String quizsId, String text, String id);
	
	public void deleteById(String id);
}
