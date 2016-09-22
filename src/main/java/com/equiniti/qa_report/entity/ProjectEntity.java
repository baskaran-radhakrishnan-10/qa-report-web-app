package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "projectstable")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="qa_report")
public class ProjectEntity implements Serializable{
	
	private static final long serialVersionUID = 2558606975176786279L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "projectid", unique = true, nullable = false)
	private int projectId;
	
	@Column(name = "projectname", length = 50)
	private String projectName;
	
	@Column(name = "obsolete")
	private boolean obsolete;

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
	
}
