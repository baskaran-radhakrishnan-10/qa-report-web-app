package com.equiniti.qa_report.event.dsr;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class DeleteDSREvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "deleteDSREvent";
	
	private List<Integer> deleteKeyList;
	
	private Map<String,Object> paramMap;
	
	private int deleteEntityKey;
	
    public DeleteDSREvent() {
        super(EVENT_NAME);
    }

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<Integer> getDeleteKeyList() {
		return deleteKeyList;
	}

	public void setDeleteKeyList(List<Integer> deleteKeyList) {
		this.deleteKeyList = deleteKeyList;
	}

	public int getDeleteEntityKey() {
		return deleteEntityKey;
	}

	public void setDeleteEntityKey(int deleteEntityKey) {
		this.deleteEntityKey = deleteEntityKey;
	}
	

}
