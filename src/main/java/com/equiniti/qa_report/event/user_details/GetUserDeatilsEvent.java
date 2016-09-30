package com.equiniti.qa_report.event.user_details;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetUserDeatilsEvent extends BaseEvent  {
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getUserDeatilsEvent";
	
	private Map<String, Object> restrictionMap;
	
	public Map<String, Object> getRestrictionMap() {
		return restrictionMap;
	}


	public void setRestrictionMap(Map<String, Object> restrictionMap) {
		this.restrictionMap = restrictionMap;
	}


	private List<User> userDetailsList;	

	private User entity;
	
    public User getEntity() {
		return entity;
	}


	public void setEntity(User entity) {
		this.entity = entity;
	}


	public GetUserDeatilsEvent() {
        super(EVENT_NAME);
    }


	public List<User> getUserDetailsList() {
		return userDetailsList;
	}


	public void setUserDetailsList(List<User> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

		



}