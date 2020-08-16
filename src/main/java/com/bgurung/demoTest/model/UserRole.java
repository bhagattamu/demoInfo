package com.bgurung.demoTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class UserRole {
	@Id
	@Column(name="role_id")
	private long roleId;
	@Column(name="role_name")
	private String userRole;
	@Column(name="role_desc")
	private String roleDesc;
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	@Override
	public String toString() {
		return "UserRole [roleId=" + roleId + ", userRole=" + userRole + ", roleDesc=" + roleDesc + "]";
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
