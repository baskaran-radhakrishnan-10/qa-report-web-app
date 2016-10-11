package com.equiniti.qa_report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_roles")
public class Roles extends GeneralEntity{

	private static final long serialVersionUID = 6494815549140172886L;

	@Column(name = "role_name", unique = true ,nullable = false)
	private String roleName;

	@Column(name = "role_desc", nullable = false)
	private String roleDesc;
	
	public final String getRoleName() {
		return this.roleName;
	}

	public final void setRoleName(final String roleName) {
		this.roleName=roleName;
	}

	
	public final String getRoleDesc() {
		return this.roleDesc;
	}

	public final void setRoleDesc(final String roleDesc) {
		this.roleDesc=roleDesc;
	}
	
/*	@Transient
	public Long getALogId() {
		return null;
	}

	@Transient
	public String getLogDeatil() {
		return null;
	}*/

}
