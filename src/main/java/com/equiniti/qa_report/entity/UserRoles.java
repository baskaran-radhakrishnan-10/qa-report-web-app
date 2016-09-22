package com.equiniti.qa_report.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "tbl_user_role")
@Proxy(lazy = false)
public class UserRoles extends GeneralEntity{
	
	private static final long serialVersionUID = -1060449243396938341L;

	private Roles roleId;

	private User userId;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	public final Roles getRoleId() {
		return this.roleId;
	}

	public final void setRoleId(final Roles roleId) {
		this.roleId=roleId;
	}


	@OneToOne
	@JoinColumn(name = "user_id")
	public final User getUserId() {
		return this.userId;
	}

	public final void setUserId(final User userId) {
		this.userId=userId;
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
