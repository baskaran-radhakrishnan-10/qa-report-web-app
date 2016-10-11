package com.equiniti.qa_report.event.rbac;


import java.util.Map;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddUserDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addUserDeatilsEvent";

	private int rowId;
	
	private String password;
	
	boolean firstLogin;
	
	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private User entity;
	
	
	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	
	public User getEntity() {
		return entity;
	}

	public void setEntity(User entity) {
		this.entity = entity;
	}

	public Map<String, Object> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Map<String, Object> requestParam) {
		this.requestParam = requestParam;
	}

	private Map<String,Object> requestParam;
	
    public AddUserDeatilsEvent() {
        super(EVENT_NAME);
    }

}

