package com.ctse.quiz_app.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Options Request Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OptionsResource {

	@NotBlank(message = "{common.not-null}")
    private String questionId;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|A|B|C|D",message="{code.pattern}")
	private String code;

	@NotBlank(message = "{common.not-null}")
	private String name;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|YES|NO",message="{common.enable-status.pattern}")
	private String isCorrect;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ACTIVE|INACTIVE",message="{common-status.pattern}")
	private String status;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
