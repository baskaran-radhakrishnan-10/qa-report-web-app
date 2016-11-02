package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LEAVEPLANSINFO")
public class LeavePlanDetails implements Serializable{

	private static final long serialVersionUID = 6523787009565422226L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;
	
	@Column(name = "USERNAME", length = 50)
	private String userName;
	
	@Column(name = "LEAVEDATES", length = 100)
	private String leaveDates;
	
	@Column(name = "LEAVETYPE", length = 50)
	private String leaveType;
	
	@Column(name = "REMARKS", length = 500)
	private String remarks;
	
	@Column(name = "LEAVEMONTH", length = 50)
	private String leaveMonth;

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

	public String getLeaveDates() {
		return leaveDates;
	}

	public void setLeaveDates(String leaveDates) {
		this.leaveDates = leaveDates;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLeaveMonth() {
		return leaveMonth;
	}

	public void setLeaveMonth(String leaveMonth) {
		this.leaveMonth = leaveMonth;
	}

}
