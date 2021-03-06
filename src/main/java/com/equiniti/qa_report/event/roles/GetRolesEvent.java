package com.equiniti.qa_report.event.roles;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetRolesEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getRolesEvent";
	
	private boolean isListAll;
	
	private Map<String,Object> restrictionMap;
	
	private List<String> rolesList;
	
	private List<Roles> entityList;
	
	public List<String> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<String> rolesList) {
		this.rolesList = rolesList;
	}

	private boolean isUniqueListRequired;
	
    public GetRolesEvent() {
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

	public List<Roles> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Roles> entityList) {
		this.entityList = entityList;
	}
    
}
