package com.equiniti.qa_report.event.btp_report;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class BuildUserReportEvent extends BaseEvent{
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "buildUserReportEvent";
	
	private Map<String,Object> paramMap; 
	
	private List<Map<String,Object>> dataObjectList;
	
    public BuildUserReportEvent() {
        super(EVENT_NAME);
    }

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<Map<String, Object>> getDataObjectList() {
		return dataObjectList;
	}

	public void setDataObjectList(List<Map<String, Object>> dataObjectList) {
		this.dataObjectList = dataObjectList;
	}

}
