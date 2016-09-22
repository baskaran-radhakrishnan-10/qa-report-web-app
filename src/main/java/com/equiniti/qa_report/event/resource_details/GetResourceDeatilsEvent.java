package com.equiniti.qa_report.event.resource_details;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetResourceDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getResourceDeatilsEvent";
	
	private boolean isListAll;
	
	private Map<String,Object> restrictionMap;
	
	private List<ResourceEntity> resourceEntityList;
	
	private List<String> resourceNameList;
	
	private boolean isUniqueListRequired;
	
    public GetResourceDeatilsEvent() {
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

	public List<ResourceEntity> getResourceEntityList() {
		return resourceEntityList;
	}

	public void setResourceEntityList(List<ResourceEntity> resourceEntityList) {
		this.resourceEntityList = resourceEntityList;
	}

	public boolean isUniqueListRequired() {
		return isUniqueListRequired;
	}

	public void setUniqueListRequired(boolean isUniqueListRequired) {
		this.isUniqueListRequired = isUniqueListRequired;
	}

	public List<String> getResourceNameList() {
		return resourceNameList;
	}

	public void setResourceNameList(List<String> resourceNameList) {
		this.resourceNameList = resourceNameList;
	}
    
}
