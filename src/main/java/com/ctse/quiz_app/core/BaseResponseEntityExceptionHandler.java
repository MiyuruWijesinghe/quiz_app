package com.ctse.quiz_app.core;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ctse.quiz_app.exception.NoRecordFoundException;
import com.ctse.quiz_app.exception.UserNotFoundException;
import com.ctse.quiz_app.exception.ValidateRecordException;
import com.ctse.quiz_app.resource.CategoryResource;
import com.ctse.quiz_app.resource.OptionsResource;
import com.ctse.quiz_app.resource.QuestionResource;
import com.ctse.quiz_app.resource.QuizResource;
import com.ctse.quiz_app.resource.SuccessAndErrorDetailsResource;
import com.ctse.quiz_app.resource.UsersResource;
import com.ctse.quiz_app.resource.ValidateResource;


@RestControllerAdvice
public class BaseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment environment;
	
	@Override 
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.invalid-url-pattern"));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.argument-type-mismatch"));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsResource.setMessages(environment.getProperty("common.user-not-found"));
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({NoRecordFoundException.class})
	public ResponseEntity<Object> noRecordFoundException(NoRecordFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.record-not-found"));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		try {
			Field sField = null;
			String fieldName = null;
			Integer index = null;
			Integer index1 = null;
			Integer atPoint = null;
			String className = ex.getBindingResult().getObjectName();
			
			switch (className) {

			case "categoryResource":
				CategoryResource categoryResource = new CategoryResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = categoryResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(categoryResource.getClass().cast(categoryResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(categoryResource, HttpStatus.UNPROCESSABLE_ENTITY);	
			case "quizResource":
				QuizResource quizResource = new QuizResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = quizResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(quizResource.getClass().cast(quizResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(quizResource, HttpStatus.UNPROCESSABLE_ENTITY);		
			case "questionResource":
				QuestionResource questionResource = new QuestionResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = questionResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(questionResource.getClass().cast(questionResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(questionResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "optionsResource":
				OptionsResource optionsResource = new OptionsResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = optionsResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(optionsResource.getClass().cast(optionsResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(optionsResource, HttpStatus.UNPROCESSABLE_ENTITY);	
			case "usersResource":
				UsersResource usersResource = new UsersResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = usersResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(usersResource.getClass().cast(usersResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(usersResource, HttpStatus.UNPROCESSABLE_ENTITY);		
			default:
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (Exception e) {
			SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
			successAndErrorDetailsResource.setMessages(environment.getProperty("common.internal-server-error"));
			successAndErrorDetailsResource.setDetails(e.getMessage());
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@ExceptionHandler({ValidateRecordException.class})
	public ResponseEntity<Object> validateRecordException(ValidateRecordException ex, WebRequest request) {
		try {
			ValidateResource typeValidation = new ValidateResource();		
			Class validationDetailClass = typeValidation.getClass();
			Field sField = validationDetailClass.getDeclaredField(ex.getField());
			sField.setAccessible(true);
			sField.set(typeValidation, ex.getMessage());		
			return new ResponseEntity<>(typeValidation, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			SuccessAndErrorDetailsResource successAndErrorDetailsDto = new SuccessAndErrorDetailsResource();
			successAndErrorDetailsDto.setMessages(environment.getProperty("common.internal-server-error"));
			successAndErrorDetailsDto.setDetails(e.getMessage());
			return new ResponseEntity<>(successAndErrorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
