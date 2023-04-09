package com.Project.demo.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", updatable = false, nullable = false, insertable = true)
	private Long userId;

	@Column(name = "USER_EMAIL", nullable = true)
	private String userEmail;

	@Column(name = "USERNAME", nullable = true)
	private String userName;

	@Column(name = "USER_PHONE_NUMBER", nullable = true)
	private Long userPhoneNumber;

	@Column(name = "USER_DESIGNATION", nullable = true)
	private String userDesignation;

	@Column(name = "PASSWORD", nullable = true)
	private String password;

	@Column(name = "USER_DOJ", nullable = true)
	private Date userDOJ;

	// @NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "FILE_ID", referencedColumnName = "FILE_ID", nullable = true, insertable = true, updatable = true)
	private Files files;

	@OneToMany(fetch = FetchType.LAZY)
	// @MapsId("userId")
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = true)
	private List<UserRoleRestriction> userRoleRestrictions;

	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public void setUserPhoneNumber(Long userPhoneNumber) {
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

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public List<UserRoleRestriction> getUserRoleRestrictions() {
		return userRoleRestrictions;
	}

	public void setUserRoleRestrictions(List<UserRoleRestriction> userRoleRestrictions) {
		this.userRoleRestrictions = userRoleRestrictions;
	}

}
