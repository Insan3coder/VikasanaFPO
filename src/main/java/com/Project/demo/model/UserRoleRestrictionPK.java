package com.Project.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleRestrictionPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID")
	private String userID;

	@Column(name = "ROLE_ID")
	private Long roleId;

	public UserRoleRestrictionPK() {
	}

	public UserRoleRestrictionPK(String userID, Long roleId) {
		this.userID = userID;
		this.roleId = roleId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}