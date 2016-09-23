package com.equiniti.qa_report.event.item_details;

import java.util.Map;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class UpdateItemDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "updateItemDetailsEvent";
	
	private ItemEntity entity;
	
	private Map<String,Object> paramMap;
	
	private boolean isUpdated=true;
	
    public UpdateItemDeatilsEvent() {
        super(EVENT_NAME);
    }

	public ItemEntity getEntity() {
		return entity;
	}

	public void setEntity(ItemEntity entity) {
		this.entity = entity;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
