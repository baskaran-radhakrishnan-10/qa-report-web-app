package com.equiniti.qa_report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_users")
public class User extends GeneralEntity{

	private static final long serialVersionUID = -1968065884219305808L;

	@Column(name = "user_id", unique = true,nullable = false)
	private  String userId;

	@Column(name = "email_id", unique = true,nullable = false)
	private  String emailId;

	@OneToOne
	@JoinColumn(name = "role_id")
	private  Roles roleId;
	
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "is_active")
	private  boolean active;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public final java.lang.String getEmailId() {
		return this.emailId;
	}

	public final void setEmailId(final java.lang.String emailId) {
		this.emailId=emailId;
	}

	public final Roles getRoleId() {
		return this.roleId;
	}

	public final void setRoleId(final Roles roleId) {
		this.roleId=roleId;
	}

	public final boolean isActive() {
		return this.active;
	}

	public final void setActive(final boolean active) {
		this.active=active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public Long getALogId() {
		return null;
	}

	@Transient
	public String getLogDeatil() {
		return null;
	}
}
