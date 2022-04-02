package com.ctse.quiz_app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ctse.quiz_app.model.Users;
import com.ctse.quiz_app.resource.UsersResource;

@Service
public interface UsersService {

	public List<Users> findAll();
	
	public Optional<Users> findById(String id);
	
	public List<Users> findByStatus(String status);
	
	public Optional<Users> findByUserId(String userId);
	
	public Users saveUser(UsersResource usersResource);
	
	public Users updateUser(String id, UsersResource usersResource);
	
	public String deleteUser(String id);
}
