package com.equiniti.qa_report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_users")
public class User extends GeneralEntity{

	private static final long serialVersionUID = -1968065884219305808L;
	
	
	@Column(name = "name", nullable = false)
	private  String userFullName;
	
	@Column(name = "user_id", unique = true,nullable = false)
	private  String userId;
	
	@Column(name = "password", unique = true,nullable = false)
	private  String password;

	@Column(name = "email_id", unique = true,nullable = false)
	private  String emailId;

	@OneToOne
	@JoinColumn(name = "role_id")
	private  Roles roleId;

	@Column(name = "is_active")
	private  boolean active;

	@Column(name = "first_login")
	private  boolean firstLogin;

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Roles getRoleId() {
		return roleId;
	}

	public void setRoleId(Roles roleId) {
		this.roleId = roleId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
}
