package com.ctse.quiz_app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.ctse.quiz_app.model.Quiz;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {

	public Optional<Quiz> findById(String id);
	
	public List<Quiz> findByStatus(String status);

	public List<Quiz> findByNameContains(String name);

	public Optional<Quiz> findByIdAndStatus(String id, String status);
	
	public List<Quiz> findByCategorysId(String categoryId);
	
	public Boolean existsByCategorysIdAndName(String categoryId, String name);

	public Boolean existsByCategorysIdAndNameAndIdNotIn(String categoryId, String name, String id);
	
	public void deleteById(String id);
	
}
