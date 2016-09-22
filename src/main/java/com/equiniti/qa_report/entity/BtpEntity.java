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
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "BtpTable")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="qa_report")
public class BtpEntity implements Serializable{
	
	private static final long serialVersionUID = 6353634667945363641L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "btpno", unique = true, nullable = false)
	private int gKey;
	
	@Column(name = "projectname", nullable = false , length = 50)
	private String projectName;
	
	@Column(name = "phase", nullable = false , length = 50)
	private String phase;
	
	@Column(name = "btpplan", nullable = false , length = 50)
	private String btpPlan;
	
	@Column(name = "sprint", nullable = false , length = 50)
	private String sPrint;
	
	@Column(name = "cycle", nullable = false , length = 50)
	private String cycle;
	
	@Column(name = "buildno", nullable = false , length = 50)
	private String buildNo;
	
	@Column(name = "btpstatus", nullable = false , length = 20)
	private String btpStatus;
	
	@Column(name = "resource1", nullable = false , length = 20)
	private String resource1;
	
	@Column(name = "resource2",  length = 20)
	private String resource2;
	
	@Column(name = "resource3",  length = 20)
	private String resource3;
	
	@Column(name = "resource4",  length = 20)
	private String resource4;
	
	@Column(name = "resource5",  length = 20)
	private String resource5;
	
	@Column(name = "resource6",  length = 20)
	private String resource6;
	
	@Column(name = "startdate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime startDate;
	
	@Column(name = "enddate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime endDate;
	
	@Column(name = "revisedenddate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime revisedEndDate;
	
	@Column(name = "btpremarks")
	private String btpRemarks;
	
	@Column(name = "createdby", nullable = false , length = 20)
	private String createdBy;
	
	@Column(name = "updatedby", nullable = false , length = 20)
	private String updatedBy;
	
	@Column(name = "createddate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdDate;
	
	@Column(name = "updateddate")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatesDate;

	public int getgKey() {
		return gKey;
	}

	public void setgKey(int gKey) {
		this.gKey = gKey;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getBtpPlan() {
		return btpPlan;
	}

	public void setBtpPlan(String btpPlan) {
		this.btpPlan = btpPlan;
	}

	public String getsPrint() {
		return sPrint;
	}

	public void setsPrint(String sPrint) {
		this.sPrint = sPrint;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getBuildNo() {
		return buildNo;
	}

	public void setBuildNo(String buildNo) {
		this.buildNo = buildNo;
	}

	public String getBtpStatus() {
		return btpStatus;
	}

	public void setBtpStatus(String btpStatus) {
		this.btpStatus = btpStatus;
	}

	public String getResource1() {
		return resource1;
	}

	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}

	public String getResource2() {
		return resource2;
	}

	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}

	public String getResource3() {
		return resource3;
	}

	public void setResource3(String resource3) {
		this.resource3 = resource3;
	}

	public String getResource4() {
		return resource4;
	}

	public void setResource4(String resource4) {
		this.resource4 = resource4;
	}

	public String getResource5() {
		return resource5;
	}

	public void setResource5(String resource5) {
		this.resource5 = resource5;
	}

	public String getResource6() {
		return resource6;
	}

	public void setResource6(String resource6) {
		this.resource6 = resource6;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public DateTime getRevisedEndDate() {
		return revisedEndDate;
	}

	public void setRevisedEndDate(DateTime revisedEndDate) {
		this.revisedEndDate = revisedEndDate;
	}

	public String getBtpRemarks() {
		return btpRemarks;
	}

	public void setBtpRemarks(String btpRemarks) {
		this.btpRemarks = btpRemarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public DateTime getUpdatesDate() {
		return updatesDate;
	}

	public void setUpdatesDate(DateTime updatesDate) {
		this.updatesDate = updatesDate;
	}
	
}
