package com.equiniti.qa_report.event.kt_plan;

import java.util.Map;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateKTPlanDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateKTPlanDetailsEvent";
	
	private KTPlan entity;
	
	private Map<String,Object> restrictionMap;
	
	private boolean isUpdated=true;
	
    public UpdateKTPlanDetailsEvent() {
        super(EVENT_NAME);
    }

	public KTPlan getEntity() {
		return entity;
	}

	public void setEntity(KTPlan entity) {
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


