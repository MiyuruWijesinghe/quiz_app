package com.ctse.quiz_app.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ctse.quiz_app.service.UsersService;

@Component
@Transactional(rollbackFor=Exception.class)
public class UsersServiceImpl implements UsersService {

	
}
