package com.equiniti.qa_report.event.item_details;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class AddItemDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "addItemDetailsEvent";
	
	private int rowId;
	
	private ItemEntity entity;
	
    public AddItemDeatilsEvent() {
        super(EVENT_NAME);
    }

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public ItemEntity getEntity() {
		return entity;
	}

	public void setEntity(ItemEntity entity) {
		this.entity = entity;
	}

}
