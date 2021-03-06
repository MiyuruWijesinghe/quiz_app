package com.ctse.quiz_app.model;

import javax.persistence.Transient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Questions Domain
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Document(collection = "Question")
public class Question {

	@Id
	private String id;
	
	@JsonIgnore
	private Quiz quizs;
	
	@Transient
    private String quizId;
	
	@Transient
    private String quizName;
	
	private String name;

	private String solution;
	
	private Boolean isLocked;
	
	private Integer selectedOptionId;
	
	private String status;
	
	private String createdUser;
	
	private String createdDate;
	
	private String modifiedUser;
	
	private String modifiedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Quiz getQuizs() {
		return quizs;
	}

	public void setQuizs(Quiz quizs) {
		this.quizs = quizs;
	}

	public String getQuizId() {
		if (quizs != null) {
			return quizs.getId();
		} else {
			return null;
		}
	}

	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		if (quizs != null) {
			return quizs.getName();
		} else {
			return null;
		}
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getSelectedOptionId() {
		return selectedOptionId;
	}

	public void setSelectedOptionId(Integer selectedOptionId) {
		this.selectedOptionId = selectedOptionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
