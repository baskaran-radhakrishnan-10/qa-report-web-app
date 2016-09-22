package com.equiniti.qa_report.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_role_resource")
public class RolesResources extends GeneralEntity{
	
	private static final long serialVersionUID = 7592908828835817154L;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Roles roleId;
	
	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resources resourceId;
	
	public  Roles roleIdProperty() {
		return this.roleId;
	}

	public  Roles getRoleId() {
		return this.roleId;
	}

	public  void setRoleId( Roles roleId) {
		this.roleId=roleId;
	}
	
	public  Resources resourceIdProperty() {
		return this.resourceId;
	}

	public  Resources getResourceId() {
		return this.resourceId;
	}

	public  void setResourceId( Resources resourceId) {
		this.resourceId=resourceId;
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
