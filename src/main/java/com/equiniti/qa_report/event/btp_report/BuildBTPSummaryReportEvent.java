package com.equiniti.qa_report.event.btp_report;

import java.util.Map;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class BuildBTPSummaryReportEvent extends BaseEvent{
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "buildBTPSummaryReportEvent";
	
	private Map<String,Object> paramMap; 
	
    public BuildBTPSummaryReportEvent() {
        super(EVENT_NAME);
    }

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

}
