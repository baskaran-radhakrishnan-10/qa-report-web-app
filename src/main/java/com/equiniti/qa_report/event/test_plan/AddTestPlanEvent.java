package com.equiniti.qa_report.event.test_plan;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddTestPlanEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addTestPlanEvent";
	
	private BtpEntity btpEntity;

    public AddTestPlanEvent() {
        super(EVENT_NAME);
    }

	public BtpEntity getBtpEntity() {
		return btpEntity;
	}

	public void setBtpEntity(BtpEntity btpEntity) {
		this.btpEntity = btpEntity;
	}

}
