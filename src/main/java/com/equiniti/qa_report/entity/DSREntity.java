package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "DSRTable")
public class DSREntity implements Serializable{

	private static final long serialVersionUID = -2887174745146398993L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sno", unique = true, nullable = false)
	private int sNo;
	
	@Column(name = "projectname", nullable = false , length = 50)
	private String projectName;
	
	@Column(name = "resource1", nullable = false , length = 20)
	private String resource;
	
	@Column(name = "dsrdate", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dsrDate;
	
	@Column(name = "plannedtask", nullable = false , length = 1000)
	private String plannedTask;
	
	@Column(name = "accomplishedtask", length = 1000)
	private String accomplishedTask;
	
	@Column(name = "dsrstatus", nullable = false , length = 20)
	private String dsrStatus;
	
	@Column(name = "remarks", length = 1000)
	private String remarks;
	
	@Column(name = "plannedhours")
	private Float plannedHours;
	
	@Column(name = "spenthours")
	private Float spentHours;

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public DateTime getDsrDate() {
		return dsrDate;
	}

	public void setDsrDate(DateTime dsrDate) {
		this.dsrDate = dsrDate;
	}

	public String getPlannedTask() {
		return plannedTask;
	}

	public void setPlannedTask(String plannedTask) {
		this.plannedTask = plannedTask;
	}

	public String getAccomplishedTask() {
		return accomplishedTask;
	}

	public void setAccomplishedTask(String accomplishedTask) {
		this.accomplishedTask = accomplishedTask;
	}

	public String getDsrStatus() {
		return dsrStatus;
	}

	public void setDsrStatus(String dsrStatus) {
		this.dsrStatus = dsrStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Float getPlannedHours() {
		return plannedHours;
	}

	public void setPlannedHours(Float plannedHours) {
		this.plannedHours = plannedHours;
	}

	public Float getSpentHours() {
		return spentHours;
	}

	public void setSpentHours(Float spentHours) {
		this.spentHours = spentHours;
	}
	
}
