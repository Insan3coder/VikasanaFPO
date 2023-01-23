package com.Project.demo.dto;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;
	private long userPhoneNumber;
	private String userDesignation;
	private String password;
	private Date userDOJ;
	private String userEmail;

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}
	//TO DO File
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(long userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserDesignation() {
		return userDesignation;
	}

	public void setUserDesignation(String userDesignation) {
		this.userDesignation = userDesignation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getUserDOJ() {
		return userDOJ;
	}
	public void setUserDOJ(Date userDOJ) {
		this.userDOJ = userDOJ;
	}




}
