package com.equiniti.qa_report.event.item_details;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class GetItemDeatilsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "getItemDetailsEvent";
	
	private boolean isListAll;
	
	private boolean isUniqueItemDescReq;
	
	private Map<String,Object> restrictionMap;
	
	private List<String> itemDescriptionList;
	
	private List<ItemEntity> itemEntityList;
	
    public GetItemDeatilsEvent() {
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

	public List<ItemEntity> getItemEntityList() {
		return itemEntityList;
	}

	public void setItemEntityList(List<ItemEntity> itemEntityList) {
		this.itemEntityList = itemEntityList;
	}

	public boolean isUniqueItemDescReq() {
		return isUniqueItemDescReq;
	}

	public void setUniqueItemDescReq(boolean isUniqueItemDescReq) {
		this.isUniqueItemDescReq = isUniqueItemDescReq;
	}

	public List<String> getItemDescriptionList() {
		return itemDescriptionList;
	}

	public void setItemDescriptionList(List<String> itemDescriptionList) {
		this.itemDescriptionList = itemDescriptionList;
	}

}
