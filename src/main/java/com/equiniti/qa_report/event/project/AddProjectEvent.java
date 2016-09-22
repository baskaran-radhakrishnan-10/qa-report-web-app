package com.equiniti.qa_report.event.project;

import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddProjectEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addProjectEvent";
	
	private int rowId;
	
	private ProjectEntity entity;
	
    public AddProjectEvent() {
        super(EVENT_NAME);
    }

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public ProjectEntity getEntity() {
		return entity;
	}

	public void setEntity(ProjectEntity entity) {
		this.entity = entity;
	}

}