package com.equiniti.qa_report.event.remainder_details;

import java.util.Map;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddRemainderDetailsEvent extends BaseEvent{

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addRemainderDetailsEvent";

	private int rowId;
	
	private Map<String,Object> requestParam;
	
	public Map<String, Object> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Map<String, Object> requestParam) {
		this.requestParam = requestParam;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	
	public AddRemainderDetailsEvent(){
		super(EVENT_NAME);
	}


}
