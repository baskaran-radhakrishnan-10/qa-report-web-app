package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.equiniti.qa_report.persistance_api.audit.api.IAuditLog;

@Entity
@Table(name = "projectstable")
public class ProjectEntity implements Serializable,IAuditLog{
	
	private static final long serialVersionUID = 2558606975176786279L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "projectid", unique = true, nullable = false)
	private int projectId;
	
	@Column(name = "projectname", length = 50)
	private String projectName;
	
	@Column(name = "obsolete")
	private boolean obsolete;
	
	@Column(name = "created_on")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;
	
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
	
	public DateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(DateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(DateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean isObsolete() {
		return obsolete;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}
	
	@Override
	@Transient
	public Long getId() {
		return (long) this.projectId;
	}

	@Override
	@Transient
	public String getLogDeatil() {
		return this.toString();
	}

	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Project Name : ").append(projectName).append(" -- ").append("Obsolete : ").append(obsolete);
		return buffer.toString();
	}
	
}
