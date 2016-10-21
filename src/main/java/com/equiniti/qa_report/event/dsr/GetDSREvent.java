package com.equiniti.qa_report.event.dsr;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetDSREvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getDSREvent";
	
	private boolean isListAll;
	
	private Map<String,Object> restrictionMap;
	
	private List<DSREntity> dsrEntityList;
	
	private DSREntity dsrEntity;

    public GetDSREvent() {
        super(EVENT_NAME);
    }

	public List<DSREntity> getDSREntityList() {
		return dsrEntityList;
	}

	public void setDSREntityList(List<DSREntity> dsrEntityList) {
		this.dsrEntityList = dsrEntityList;
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

	public DSREntity getDSREntity() {
		return dsrEntity;
	}

	public void setDSREntity(DSREntity dsrEntity) {
		this.dsrEntity = dsrEntity;
	}
    
}
