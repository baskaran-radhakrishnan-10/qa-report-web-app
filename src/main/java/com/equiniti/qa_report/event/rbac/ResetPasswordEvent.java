package com.equiniti.qa_report.event.rbac;

import java.util.Map;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class ResetPasswordEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "resetPasswordEvent";
	
	private User entity;
	
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Map<String,Object> paramMap;
	
	private boolean isUpdated=true;
	
    public ResetPasswordEvent() {
        super(EVENT_NAME);
    }

	public User getEntity() {
		return entity;
	}

	public void setEntity(User entity) {
		this.entity = entity;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
