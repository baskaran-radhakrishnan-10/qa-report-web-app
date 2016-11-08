package com.equiniti.qa_report.event.leave_details;

import java.util.Map;

import com.equiniti.qa_report.entity.LeaveDetails;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateLeaveDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateLeaveDetailsEvent";
	
	private LeaveDetails leaveDetailsEntity;
	
	private Map<String,Object> paramMap;
	
	private boolean isUpdated=true;

    public UpdateLeaveDetailsEvent() {
        super(EVENT_NAME);
    }
    
	public LeaveDetails getLeaveDetailsEntity() {
		return leaveDetailsEntity;
	}
	public void setLeaveDetailsEntity(LeaveDetails leaveDetailsEntity) {
		this.leaveDetailsEntity = leaveDetailsEntity;
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
