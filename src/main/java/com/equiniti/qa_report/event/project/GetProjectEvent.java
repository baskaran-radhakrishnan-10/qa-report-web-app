package com.equiniti.qa_report.event.project;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetProjectEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getProjectEvent";
	
	private boolean isListAll;
	
	private Map<String,Object> restrictionMap;
	
	private List<ProjectEntity> projectEntityList;
	
	private List<String> projectNameList;
	
	private boolean isUniqueListRequired;
	
    public GetProjectEvent() {
        super(EVENT_NAME);
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

	public boolean isUniqueListRequired() {
		return isUniqueListRequired;
	}

	public void setUniqueListRequired(boolean isUniqueListRequired) {
		this.isUniqueListRequired = isUniqueListRequired;
	}

	public List<ProjectEntity> getProjectEntityList() {
		return projectEntityList;
	}

	public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
		this.projectEntityList = projectEntityList;
	}

	public List<String> getProjectNameList() {
		return projectNameList;
	}

	public void setProjectNameList(List<String> projectNameList) {
		this.projectNameList = projectNameList;
	}
    
}
