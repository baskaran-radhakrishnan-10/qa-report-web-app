package com.equiniti.qa_report.event.item_details;

import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseEvent;

public class DeleteItemDetailsEvent extends BaseEvent {

	private static final long serialVersionUID = -2571076832951245757L;

	private static final String EVENT_NAME = "deleteItemDetailsEvent";
	
	private int btpNo;
	
	private int itemNo;
	
    public DeleteItemDetailsEvent() {
        super(EVENT_NAME);
    }

	public int getBtpNo() {
		return btpNo;
	}

	public void setBtpNo(int btpNo) {
		this.btpNo = btpNo;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

}
