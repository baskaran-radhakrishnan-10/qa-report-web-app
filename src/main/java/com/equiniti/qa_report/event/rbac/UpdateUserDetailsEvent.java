package com.equiniti.qa_report.event.rbac;

import java.util.Map;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateUserDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateUserDetailsEvent";
	
	private User entity;
	
	private Map<String,Object> restrictionMap;
	
	private boolean isUpdated=true;
	
    public UpdateUserDetailsEvent() {
        super(EVENT_NAME);
    }

	public User getEntity() {
		return entity;
	}

	public void setEntity(User entity) {
		this.entity = entity;
	}

	public Map<String, Object> getRestrictionMap() {
		return restrictionMap;
	}

	public void setRestrictionMap(Map<String, Object> restrictionMap) {
		this.restrictionMap = restrictionMap;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
}


