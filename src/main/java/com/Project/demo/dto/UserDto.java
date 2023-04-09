package com.Project.demo.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private long userPhoneNumber;
	private String userDesignation;
	private String password;
	private Date userDOJ;
	private String userEmail;
	private String fileType;
	private Long fileId;
	private String fileName;
	private String filePath;
	private String fileContent;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileContent() {
		return fileContent;
	}

	private List<String> userRoleRestrictions;

	public List<String> getUserRoleRestrictions() {
		return userRoleRestrictions;
	}

	public void setUserRoleRestrictions(List<String> userRoleRestrictions) {
		this.userRoleRestrictions = userRoleRestrictions;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	// TO DO File
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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
