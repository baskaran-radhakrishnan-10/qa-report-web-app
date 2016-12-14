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
@Table(name = "KTTable")
public class KTPlan implements Serializable,IAuditLog{
	
private static final long serialVersionUID = -1968065884219305808L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tri_id", unique = true, nullable = false)
	private int gkey;
	
	@Column(name = "project" , nullable = false)
	private  String project;
	
	@Column(name = "tri_type", nullable = false)
	private  String trainingType;

	@Column(name = "session", nullable = false)
	private  String session;
	
	@Column(name = "title", nullable = false)
	private  String title;
	
	@Column(name = "trainers", nullable = false)
	private  String trainers;
	
	@Column(name = "others")
	private  String others;
	
	@Column(name = "attendees", nullable = false)
	private  String attendees;
	
	@Column(name = "location", nullable = false)
	private  String location;
	
	@Column(name = "startdate" , nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime startDate;
	
	@Column(name = "enddate" , nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime endDate;

	@Column(name = "duration", nullable = false)
	private double duration;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "feedback")
	private String feedback;
	
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

	public int getGkey() {
		return gkey;
	}

	public void setGkey(int gkey) {
		this.gkey = gkey;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTrainers() {
		return trainers;
	}

	public void setTrainers(String trainers) {
		this.trainers = trainers;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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
		buffer.append("Project : ").append(project).append(" -- ").append("Training Type : ").append(trainingType).append(" -- ");
		buffer.append("Session : ").append(session).append(" -- ").append("Title : ").append(title);
		buffer.append("Trainers : ").append(trainers).append(" -- ").append("Attendees : ").append(attendees);
		return buffer.toString();
	}
}
