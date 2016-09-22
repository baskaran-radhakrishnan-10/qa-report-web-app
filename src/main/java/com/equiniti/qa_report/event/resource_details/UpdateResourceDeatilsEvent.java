package com.equiniti.qa_report.event.resource_details;

import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateResourceDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateResourceDeatilsEvent";
	
	private ResourceEntity entity;
	
    public UpdateResourceDeatilsEvent() {
        super(EVENT_NAME);
    }

	public ResourceEntity getEntity() {
		return entity;
	}

	public void setEntity(ResourceEntity entity) {
		this.entity = entity;
	}
}
