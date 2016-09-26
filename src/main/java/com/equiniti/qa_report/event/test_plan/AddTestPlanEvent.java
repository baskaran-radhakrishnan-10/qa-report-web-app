package com.equiniti.qa_report.event.test_plan;

import java.util.Map;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddTestPlanEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addTestPlanEvent";
	
	private BtpEntity btpEntity;
	
	private Map<String,Object> paramMap;
	
	private int addedRow;

    public AddTestPlanEvent() {
        super(EVENT_NAME);
    }

	public BtpEntity getBtpEntity() {
		return btpEntity;
	}

	public void setBtpEntity(BtpEntity btpEntity) {
		this.btpEntity = btpEntity;
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
