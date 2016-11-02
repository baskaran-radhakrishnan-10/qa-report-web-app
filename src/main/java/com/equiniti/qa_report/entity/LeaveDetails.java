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
@Table(name = "LEAVEDETAILS")
public class LeaveDetails implements Serializable{

	private static final long serialVersionUID = 3363611099322662906L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;
	
	@Column(name = "USERNAME", nullable = false , length = 50)
	private String userName;
	
	@Column(name = "LEAVEDATE", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime leaveDate;
	
	@Column(name = "LEAVETYPE", nullable = false , length = 50)
	private String leaveType;
	
	@Column(name = "LEAVEDESCRIPTION", nullable = false , length = 50)
	private String leaveDescription;
	
	@Column(name = "ISAPPROVED")
	private  boolean approved;
	
	@Column(name = "APPROVEDBY", length = 50)
	private String approvedBy;
	
	@Column(name = "Remarks", length = 500)
	private String remarks;
	
	@Column(name = "LEAVETIME", length = 10)
	private String leaveTime;

	public int getgKey() {
		return gKey;
	}

	public void setgKey(int gKey) {
		this.gKey = gKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public DateTime getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(DateTime leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDescription() {
		return leaveDescription;
	}

	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

}
