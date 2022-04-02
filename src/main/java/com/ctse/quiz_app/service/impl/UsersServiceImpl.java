package com.ctse.quiz_app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ctse.quiz_app.exception.NoRecordFoundException;
import com.ctse.quiz_app.exception.ValidateRecordException;
import com.ctse.quiz_app.model.Users;
import com.ctse.quiz_app.repository.UsersRepository;
import com.ctse.quiz_app.resource.UsersResource;
import com.ctse.quiz_app.service.UsersService;

/**
 * Users Service Implementation
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
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UsersRepository usersRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}

	@Override
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public Optional<Users> findById(String id) {
		Optional<Users> users = usersRepository.findById(id);
		if (users.isPresent()) {
			return Optional.ofNullable(users.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Users> findByStatus(String status) {
		return usersRepository.findByStatus(status);
	}

	@Override
	public Optional<Users> findByUserId(String userId) {
		Optional<Users> users = usersRepository.findByUserId(userId);
		if (users.isPresent()) {
			return Optional.ofNullable(users.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Users saveUser(UsersResource usersResource) {
		Users users = new Users();
		
        if (usersRepository.existsByUserId(usersResource.getUserId())) {
        	throw new ValidateRecordException(environment.getProperty("userId.duplicate"), "message");
		}

        if (!usersResource.getPassword().equalsIgnoreCase(usersResource.getPasswordConfirm())) {
        	throw new ValidateRecordException(environment.getProperty("password.not-match"), "message");
        }
        
        users.setFirstName(usersResource.getFirstName());
        users.setLastName(usersResource.getLastName());
        users.setEmail(usersResource.getEmail());
        users.setPassword(usersResource.getPassword());
        users.setUserId(usersResource.getUserId());
        users.setRole(usersResource.getRole());
        users.setStatus(usersResource.getStatus());
        users.setCreatedDate(formatDate(new Date()));
        usersRepository.save(users);
		return users;
	}

	@Override
	public Users updateUser(String id, UsersResource usersResource) {
		Optional<Users> isPresentUsers = usersRepository.findById(id);
		if (!isPresentUsers.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (usersRepository.existsByUserIdAndIdNotIn(usersResource.getUserId(), id)) {
			throw new ValidateRecordException(environment.getProperty("userId.duplicate"), "message");
		}
		
		if (!usersResource.getPassword().equalsIgnoreCase(usersResource.getPasswordConfirm())) {
        	throw new ValidateRecordException(environment.getProperty("password.not-match"), "message");
        }
		
		Users users = isPresentUsers.get();
		users.setFirstName(usersResource.getFirstName());
        users.setLastName(usersResource.getLastName());
        users.setEmail(usersResource.getEmail());
        users.setPassword(usersResource.getPassword());
        users.setUserId(usersResource.getUserId());
        users.setRole(usersResource.getRole());
        users.setStatus(usersResource.getStatus());
        users.setModifiedDate(formatDate(new Date()));
        usersRepository.save(users);
		return users;
	}

	@Override
	public String deleteUser(String id) {
		Optional<Users> isPresentUser = usersRepository.findById(id);
		if (!isPresentUser.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		usersRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}
	
}
