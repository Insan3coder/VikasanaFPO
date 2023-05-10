package com.Project.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", updatable = false, nullable = false)
	private Long roleId;

	@Column(name = "ROLE_NAME", nullable = false, unique = true)
	private String roleName;

	@OneToMany(fetch = FetchType.LAZY)
	// @MapsId("userId")
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private List<UserRoleRestriction> userRoleRestrictions;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
