package com.equiniti.qa_report.event.btp_report;

import java.util.Map;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class BuildBTPMonthlyReportEvent extends BaseEvent{
	
	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "buildBTPMonthlyReportEvent";
	
	private Map<String,Object> paramMap;
	
	private boolean isEmptyResult = false;
	
    public BuildBTPMonthlyReportEvent() {
        super(EVENT_NAME);
    }

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public boolean isEmptyResult() {
		return isEmptyResult;
	}

	public void setEmptyResult(boolean isEmptyResult) {
		this.isEmptyResult = isEmptyResult;
	}

}
