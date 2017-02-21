package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.equiniti.qa_report.persistance_api.audit.api.IAuditLog;

public class SecurityQuestionEntity implements Serializable,IAuditLog{

	private static final long serialVersionUID = -304632985174573832L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;
	
	@Column(name = "question", unique = true,nullable = false)
	private  String question;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getgKey() {
		return gKey;
	}

	public void setgKey(int gKey) {
		this.gKey = gKey;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	@Transient
	public Long getId() {
		return null;
	}

	@Override
	@Transient
	public String getLogDeatil() {
		return null;
	}

}
