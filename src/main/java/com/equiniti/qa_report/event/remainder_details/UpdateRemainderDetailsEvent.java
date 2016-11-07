package com.equiniti.qa_report.event.remainder_details;

import java.util.Map;

import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateRemainderDetailsEvent extends BaseEvent {
	

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateRemainderDetailsEvent";
	
	private Remainder entity;
	
	private Map<String,Object> restrictionMap;
	
	private boolean isUpdated=true;

	public Remainder getEntity() {
		return entity;
	}

	public void setEntity(Remainder entity) {
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
	
	 public UpdateRemainderDetailsEvent() {
	        super(EVENT_NAME);
	    }

}
