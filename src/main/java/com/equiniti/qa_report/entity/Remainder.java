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
@Table(name="REMAINDERS")
public class Remainder implements Serializable,IAuditLog{
	
	private static final long serialVersionUID = 5342632135099568516L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gkey", unique = true, nullable = false)
	private int gkey;
	
	@Column(name = "REMAINDERDATE")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime remainderDate;
	
	@Column(name="REMAINDERTIME")
	private String remainderTime;
	
	@Column(name="MESSAGE")
	private String remainderMessage;
	
	@Column(name="USER_ID",nullable = true)
	private String remainderUser;
	
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
	
	public String getRemainderUser() {
		return remainderUser;
	}

	public void setRemainderUser(String remainderUser) {
		this.remainderUser = remainderUser;
	}

	public int getGkey() {
		return gkey;
	}

	public void setGkey(int gkey) {
		this.gkey = gkey;
	}

	public DateTime getRemainderDate() {
		return remainderDate;
	}

	public void setRemainderDate(DateTime remainderDate) {
		this.remainderDate = remainderDate;
	}

	public String getRemainderTime() {
		return remainderTime;
	}

	public void setRemainderTime(String remainderTime) {
		this.remainderTime = remainderTime;
	}

	public String getRemainderMessage() {
		return remainderMessage;
	}

	public void setRemainderMessage(String remainderMessage) {
		this.remainderMessage = remainderMessage;
	}
	
	@Override
	@Transient
	public Long getId() {
		return (long) this.gkey;
	}

	@Override
	@Transient
	public String getLogDeatil() {
		return this.toString();
	}

	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("User Name : ").append(remainderUser).append(" -- ").append("remainderDate : ").append(remainderDate).append(" -- ");
		buffer.append("remainderTime : ").append(remainderTime).append(" -- ").append("Message : ").append(remainderMessage);
		return buffer.toString();
	}

}
