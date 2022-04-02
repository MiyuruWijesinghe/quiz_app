package com.ctse.quiz_app.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Users Request Resource
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   25-03-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UsersResource {

	@NotBlank(message = "{common.not-null}")
    private String firstName;
	
	@NotBlank(message = "{common.not-null}")
	private String lastName;

	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "{common.email-pattern}")
	private String email;
	
	@NotBlank(message = "{common.not-null}")
	private String userId;
	
	@NotBlank(message = "{common.not-null}")
	private String password;
	
	@NotBlank(message = "{common.not-null}")
	private String passwordConfirm;

	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ADMIN|USER",message="{roles.pattern}")
	private String role;
	
	@NotBlank(message = "{common.not-null}")
	@Pattern(regexp = "^$|ACTIVE|INACTIVE",message="{common-status.pattern}")
	private String status;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
