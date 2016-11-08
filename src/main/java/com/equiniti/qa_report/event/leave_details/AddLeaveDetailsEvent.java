package com.equiniti.qa_report.event.leave_details;

import java.util.Map;

import com.equiniti.qa_report.entity.LeaveDetails;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddLeaveDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addLeaveDetailsEvent";
	
	private LeaveDetails leaveDetailsEntity;
	
	private Map<String,Object> paramMap;
	
	private int addedRow;

    public AddLeaveDetailsEvent() {
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

	public int getAddedRow() {
		return addedRow;
	}

	public void setAddedRow(int addedRow) {
		this.addedRow = addedRow;
	}

}
