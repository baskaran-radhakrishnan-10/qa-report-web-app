package com.equiniti.qa_report.form.model;

import java.io.Serializable;

public class LoginModelAttribute extends AbstractModelAttribute implements Serializable{
	
	private static final long serialVersionUID = -7875483602730392110L;
	
	private String accountId;
	
	private String userId;
	
	private String password;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
