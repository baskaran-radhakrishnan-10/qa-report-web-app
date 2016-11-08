package com.equiniti.qa_report.event.remainder_details;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetRemainderDetailsEvent extends BaseEvent {
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getRemainderDetailsEvent";
	
	private List<Remainder> remainderDetailsList;	

	private Remainder entity;
	
	private Map<String, Object> restrictionMap;
	
	public Map<String, Object> getRestrictionMap() {
		return restrictionMap;
	}

	public void setRestrictionMap(Map<String, Object> restrictionMap) {
		this.restrictionMap = restrictionMap;
	}

	public List<Remainder> getRemainderDetailsList() {
		return remainderDetailsList;
	}

	public void setRemainderDetailsList(List<Remainder> remainderDetailsList) {
		this.remainderDetailsList = remainderDetailsList;
	}

	public Remainder getEntity() {
		return entity;
	}

	public void setEntity(Remainder entity) {
		this.entity = entity;
	}

	public GetRemainderDetailsEvent() {
        super(EVENT_NAME);
    }

}
