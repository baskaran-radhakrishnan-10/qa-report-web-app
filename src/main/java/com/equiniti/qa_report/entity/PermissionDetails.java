package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSIONDETAILS")
public class PermissionDetails implements Serializable{

	private static final long serialVersionUID = -4144333258155269470L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;
	
	@Column(name = "USERNAME", nullable = false , length = 50)
	private String userName;
	
	@Column(name = "PERMISSIONDATE", nullable = false , length = 50)
	private String permissionDate;
	
	@Column(name = "PERMISSIONTIMESTART", nullable = false , length = 10)
	private String permissionStart;
	
	@Column(name = "PERMISSIONTIMEEND", nullable = false , length = 10)
	private String permissionEnd;
	
	@Column(name = "PERMISSIONHRS", nullable = false , length = 10)
	private String permissionHours;
	
	@Column(name = "APPROVED", length = 50)
	private  boolean approved;
	
	@Column(name = "APPROVEDBY", length = 50)
	private String approvedBy;

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

	public String getPermissionDate() {
		return permissionDate;
	}

	public void setPermissionDate(String permissionDate) {
		this.permissionDate = permissionDate;
	}

	public String getPermissionStart() {
		return permissionStart;
	}

	public void setPermissionStart(String permissionStart) {
		this.permissionStart = permissionStart;
	}

	public String getPermissionEnd() {
		return permissionEnd;
	}

	public void setPermissionEnd(String permissionEnd) {
		this.permissionEnd = permissionEnd;
	}

	public String getPermissionHours() {
		return permissionHours;
	}

	public void setPermissionHours(String permissionHours) {
		this.permissionHours = permissionHours;
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

}
