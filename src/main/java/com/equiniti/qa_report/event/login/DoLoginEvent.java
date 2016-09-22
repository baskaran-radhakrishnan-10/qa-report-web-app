package com.equiniti.qa_report.event.login;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class DoLoginEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "doLoginEvent";

    private String userId;
    
    private String password;
    
    private User user;
    
    public DoLoginEvent() {
        super(EVENT_NAME);
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
