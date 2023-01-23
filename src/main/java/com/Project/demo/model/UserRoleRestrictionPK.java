package com.Project.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleRestrictionPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID")
	private Long userID;

	@Column(name = "ROLE_ID")
	private Long roleId;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}