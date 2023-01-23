package com.Project.demo.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", updatable = false, nullable = false)
	private Long userId;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "USER_PHONE_NUMBER")
	private Long userPhoneNumber;

	/*
	 * @Column(name = "EMPLOYEE_EMAIL", unique = true) private String employeeEmail;
	 * 
	 * @Column(name = "IS_MANAGER") private boolean isManager;
	 */

	@Column(name = "USER_DESIGNATION")
	private String userDesignation;

	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "USER_DOJ")
	private Date userDOJ;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId("fileId")
	@JoinColumn(name = "FILE_ID", referencedColumnName = "FILE_ID")
	private Files files;
	
	
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

	/*
	 * // @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY) private
	 * List<EmployeeToTechnology> technologies;
	 * 
	 * @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY) private
	 * List<EmployeeToProject> projects;
	 * 
	 * public List<EmployeeToProject> getProjects() { return projects; }
	 * 
	 * public void setProjects(List<EmployeeToProject> projects) { this.projects =
	 * projects; }
	 */


}
