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
	private String userDesgination;
	private String password;
	private Date userDOJ;
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
	public String getUserDesgination() {
		return userDesgination;
	}
	public void setUserDesgination(String userDesgination) {
		this.userDesgination = userDesgination;
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
