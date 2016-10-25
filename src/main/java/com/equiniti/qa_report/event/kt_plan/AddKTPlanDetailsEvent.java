package com.equiniti.qa_report.event.kt_plan;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddKTPlanDetailsEvent extends BaseEvent {
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addKTPlanDetailsEvent";
	private Map<String, Object> restrictionMap;
	private List<KTPlan> ktPlanList;	
	private KTPlan entity;
	private Map<String,Object> requestParam;
	private int rowId;
	
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public Map<String, Object> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Map<String, Object> requestParam) {
		this.requestParam = requestParam;
	}

	public Map<String, Object> getRestrictionMap() {
		return restrictionMap;
	}

	public void setRestrictionMap(Map<String, Object> restrictionMap) {
		this.restrictionMap = restrictionMap;
	}
	
	public List<KTPlan> getKtPlansList() {
		return ktPlanList;
	}

	public void setKtPlansList(List<KTPlan> ktPlansList) {
		this.ktPlanList = ktPlansList;
	}

	public KTPlan getEntity() {
		return entity;
	}

	public void setEntity(KTPlan entity) {
		this.entity = entity;
	}
	
	public AddKTPlanDetailsEvent() {
        super(EVENT_NAME);
    }
}
