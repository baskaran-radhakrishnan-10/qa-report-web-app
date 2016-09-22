package com.equiniti.qa_report.event.resource_details;

import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddResourceDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addResourceDeatilsEvent";
	
	private int rowId;
	
	private ResourceEntity entity;
	
    public AddResourceDeatilsEvent() {
        super(EVENT_NAME);
    }

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public ResourceEntity getEntity() {
		return entity;
	}

	public void setEntity(ResourceEntity entity) {
		this.entity = entity;
	}
    
    

}
