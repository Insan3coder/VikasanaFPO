package com.Project.demo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE_RESTRICTION")
public class UserRoleRestriction {

	@EmbeddedId
	UserRoleRestrictionPK userRoleRestrictionPK;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"/*
																	 * , insertable = false, updatable = false, nullable
																	 * = false
																	 */)
	private Users users;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("roleId")
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID" /*
																	 * , insertable = false, updatable = false, nullable
																	 * = false
																	 */)
	private Roles roles;

	public UserRoleRestrictionPK getUserRoleRestrictionPK() {
		return userRoleRestrictionPK;
	}

	public void setUserRoleRestrictionPK(UserRoleRestrictionPK userRoleRestrictionPK) {
		this.userRoleRestrictionPK = userRoleRestrictionPK;
	}

	public UserRoleRestriction() {
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}
