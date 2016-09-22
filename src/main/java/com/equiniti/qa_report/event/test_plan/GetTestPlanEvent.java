package com.equiniti.qa_report.event.test_plan;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetTestPlanEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getTestPlanEvent";
	
	private boolean isListAll;
	
	private Map<String,Object> restrictionMap;
	
	private List<BtpEntity> btpEntityList;
	
	private BtpEntity btpEntity;

    public GetTestPlanEvent() {
        super(EVENT_NAME);
    }

	public List<BtpEntity> getBtpEntityList() {
		return btpEntityList;
	}

	public void setBtpEntityList(List<BtpEntity> btpEntityList) {
		this.btpEntityList = btpEntityList;
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

	public BtpEntity getBtpEntity() {
		return btpEntity;
	}

	public void setBtpEntity(BtpEntity btpEntity) {
		this.btpEntity = btpEntity;
	}
    
}
