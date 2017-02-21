package com.equiniti.qa_report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.equiniti.qa_report.persistance_api.audit.api.IAuditLog;

@Entity
@Table(name = "tbl_users")
public class User extends GeneralEntity implements IAuditLog{

	private static final long serialVersionUID = -1968065884219305808L;
	
	@Column(name = "name", nullable = false)
	private  String userFullName;
	
	@Column(name = "user_id", unique = true,nullable = false)
	private  String userId;
	
	@Column(name = "password" ,nullable = false)
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
	
	@Column(name = "is_deleted")
	private  boolean deleted;
	
	/*@Column(name = "deleted_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deletedTime;

	public DateTime getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(DateTime deletedTime) {
		this.deletedTime = deletedTime;
	}*/


	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

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

	@Override
	@Transient
	public Long getId() {
		return (long) this.getGkey();
	}

	@Override
	@Transient
	public String getLogDeatil() {
		return this.toString();
	}

	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("User Full Name : ").append(userFullName).append(" -- ").append("User Id : ").append(userId).append(" -- ");
		buffer.append("Email Id : ").append(emailId).append(" -- ").append("Role Id : ").append(roleId.getRoleName());
		return buffer.toString();
	}
}
