package com.equiniti.qa_report.event.rbac;

import java.util.List;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetUniqueUserListEvent extends BaseEvent  {
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getUserDeatilsEvent";
	
	private List<String> uniqueUserList;

	public List<String> getUniqueUserList() {
		return uniqueUserList;
	}

	public void setUniqueUserList(List<String> uniqueUserList) {
		this.uniqueUserList = uniqueUserList;
	}

	 public GetUniqueUserListEvent() {
	        super(EVENT_NAME);
	    }

}
