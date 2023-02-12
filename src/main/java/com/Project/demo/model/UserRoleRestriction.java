package com.Project.demo.model;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE_RESTRICTION")
public class UserRoleRestriction {

	@EmbeddedId
	UserRoleRestrictionPK userRoleRestrictionPK;

	@ManyToMany(fetch = FetchType.LAZY)
	// @MapsId("userId")
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = true)
	private List<Users> users;

	@ManyToMany(fetch = FetchType.LAZY)
	// @MapsId("roleId")
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = true)
	private List<Roles> roles;

	public UserRoleRestrictionPK getUserRoleRestrictionPK() {
		return userRoleRestrictionPK;
	}

	public void setUserRoleRestrictionPK(UserRoleRestrictionPK userRoleRestrictionPK) {
		this.userRoleRestrictionPK = userRoleRestrictionPK;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public UserRoleRestriction() {
	}


}
