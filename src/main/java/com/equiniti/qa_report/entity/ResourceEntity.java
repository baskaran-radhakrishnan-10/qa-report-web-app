package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.equiniti.qa_report.persistance_api.audit.api.IAuditLog;

@Entity
@Table(name = "ResourceTable")
public class ResourceEntity implements Serializable,IAuditLog{
	
	private static final long serialVersionUID = 6132396313576051532L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;

	@Column(name = "itemno", nullable = false)
	private int itemNo;
	
	@OneToOne
	@JoinColumn(name = "btpno",nullable = false)
	private BtpEntity btpNo;
	
	@Column(name = "itemname", nullable = false , length = 50)
	private String itemName;
	
	@Column(name = "resourcename", nullable = false , length = 20)
	private String resourceName;
	
	@Column(name = "itemcount", nullable = false )
	private int itemCount;
	
	@Column(name = "acttime", nullable = false )
	private float actTime;
	
	@Column(name = "bugslogged", nullable = false )
	private int bugsLogged;
	
	@Column(name = "pass")
	private int pass;
	
	@Column(name = "fail")
	private int fail;
	
	@Column(name = "clarification")
	private int clarification;
	
	@Column(name = "unabletotest")
	private int unableToSet;
	
	@Column(name = "pending")
	private int pending;
	
	@Column(name = "blocked")
	private int blocked;
	
	@Column(name = "is_deleted")
	private  boolean deleted;
	
	@Column(name = "deleted_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime deletedTime;

	public DateTime getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(DateTime deletedTime) {
		this.deletedTime = deletedTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public BtpEntity getBtpNo() {
		return btpNo;
	}

	public void setBtpNo(BtpEntity btpNo) {
		this.btpNo = btpNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public float getActTime() {
		return actTime;
	}

	public void setActTime(float actTime) {
		this.actTime = actTime;
	}

	public int getBugsLogged() {
		return bugsLogged;
	}

	public void setBugsLogged(int bugsLogged) {
		this.bugsLogged = bugsLogged;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getFail() {
		return fail;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	public int getClarification() {
		return clarification;
	}

	public void setClarification(int clarification) {
		this.clarification = clarification;
	}

	public int getUnableToSet() {
		return unableToSet;
	}

	public void setUnableToSet(int unableToSet) {
		this.unableToSet = unableToSet;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public int getgKey() {
		return gKey;
	}

	public void setgKey(int gKey) {
		this.gKey = gKey;
	}
	
	@Override
	@Transient
	public Long getId() {
		return (long) this.gKey;
	}

	@Override
	@Transient
	public String getLogDeatil() {
		return this.toString();
	}

	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Item No : ").append(itemNo).append(" -- ").append("BTP No : ").append(btpNo).append(" -- ");
		buffer.append("Item Name : ").append(itemName).append(" -- ").append("Resource Name : ").append(resourceName);
		buffer.append("Item Count : ").append(itemCount).append(" -- ").append("Actual Time : ").append(actTime);
		buffer.append("Bugs Logged : ").append(bugsLogged).append(" -- ").append("Pass : ").append(pass);
		buffer.append("Fail : ").append(fail).append(" -- ").append("Pending : ").append(pending);
		return buffer.toString();
	}
}
