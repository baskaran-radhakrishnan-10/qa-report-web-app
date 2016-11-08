package com.equiniti.qa_report.event.leave_details;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.LeaveDetails;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetLeaveDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getLeaveDetailsEvent";
	
	private boolean isListAll;
	
	private boolean isFilter;
	
	private Map<String,Object> restrictionMap;
	
	private List<LeaveDetails> leaveDetailsEntityList;
	
	private LeaveDetails leaveDetailsEntity;

    public GetLeaveDetailsEvent() {
        super(EVENT_NAME);
    }

	public boolean isListAll() {
		return isListAll;
	}

	public void setListAll(boolean isListAll) {
		this.isListAll = isListAll;
	}

	public Map<String, Object> getRestrictionMap() {
		return restrictionMap;
	}

	public void setRestrictionMap(Map<String, Object> restrictionMap) {
		this.restrictionMap = restrictionMap;
	}

	public boolean isFilter() {
		return isFilter;
	}

	public void setFilter(boolean isFilter) {
		this.isFilter = isFilter;
	}

	public List<LeaveDetails> getLeaveDetailsEntityList() {
		return leaveDetailsEntityList;
	}

	public void setLeaveDetailsEntityList(List<LeaveDetails> leaveDetailsEntityList) {
		this.leaveDetailsEntityList = leaveDetailsEntityList;
	}

	public LeaveDetails getLeaveDetailsEntity() {
		return leaveDetailsEntity;
	}

	public void setLeaveDetailsEntity(LeaveDetails leaveDetailsEntity) {
		this.leaveDetailsEntity = leaveDetailsEntity;
	}
    
}
