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

public class SecurityAnswerEntity implements Serializable,IAuditLog{

	private static final long serialVersionUID = 5770730149157900763L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;
	
	@Column(name = "answer", unique = true,nullable = false)
	private  String answer;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getgKey() {
		return gKey;
	}

	public void setgKey(int gKey) {
		this.gKey = gKey;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
