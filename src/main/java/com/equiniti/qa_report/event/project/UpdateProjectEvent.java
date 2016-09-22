package com.equiniti.qa_report.event.project;

import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateProjectEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateProjectEvent";
	
	private ProjectEntity entity;
	
    public UpdateProjectEvent() {
        super(EVENT_NAME);
    }

	public ProjectEntity getEntity() {
		return entity;
	}

	public void setEntity(ProjectEntity entity) {
		this.entity = entity;
	}
	
}
