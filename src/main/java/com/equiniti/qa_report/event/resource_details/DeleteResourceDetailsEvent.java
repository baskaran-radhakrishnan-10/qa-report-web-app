package com.equiniti.qa_report.event.resource_details;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class DeleteResourceDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "deleteResourceDetailsEvent";
	
	private int gKey;
	
    public DeleteResourceDetailsEvent() {
        super(EVENT_NAME);
    }

	public int getgKey() {
		return gKey;
	}

	public void setgKey(int gKey) {
		this.gKey = gKey;
	}


}
