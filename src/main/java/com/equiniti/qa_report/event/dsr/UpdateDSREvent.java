package com.equiniti.qa_report.event.dsr;

import java.util.Map;

import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateDSREvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateDSREvent";
	
	private DSREntity dsrEntity;
	
	private Map<String,Object> paramMap;
	
	private boolean isUpdated=true;

    public UpdateDSREvent() {
        super(EVENT_NAME);
    }

	public DSREntity getDSREntity() {
		return dsrEntity;
	}

	public void setDSREntity(DSREntity dsrEntity) {
		this.dsrEntity = dsrEntity;
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
