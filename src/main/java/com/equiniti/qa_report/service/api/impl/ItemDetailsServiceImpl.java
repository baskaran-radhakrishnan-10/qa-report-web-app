package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.event.item_details.AddItemDeatilsEvent;
import com.equiniti.qa_report.event.item_details.GetItemDeatilsEvent;
import com.equiniti.qa_report.event.item_details.UpdateItemDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ItemDetailsService;

@Service("itemDetailsService")
public class ItemDetailsServiceImpl extends BaseAPIImpl implements ItemDetailsService{
	
	@Override
	public List<ItemEntity> getItemDetailsByItem(Map<String, Object> inputParam) throws APIException {
		GetItemDeatilsEvent event=null;
		try{
			event=getEvent(GetItemDeatilsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getItemEntityList();
	}
	
	@Override
	public List<String> getUniqueItemDescList() throws APIException {
		GetItemDeatilsEvent event=null;
		try{
			event=getEvent(GetItemDeatilsEvent.class);
			event.setUniqueItemDescReq(true);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getItemDescriptionList();
	}

	@Override
	public int addItemDetails(Map<String, Object> inputParam) throws APIException {
		AddItemDeatilsEvent event=null;
		try{
			event=getEvent(AddItemDeatilsEvent.class);
			event.setRequestParam(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getRowId();
	}

	@Override
	public boolean updateItemDeatils(Map<String, Object> inputParam) throws APIException {
		UpdateItemDeatilsEvent event=null;
		try{
			event=getEvent(UpdateItemDeatilsEvent.class);
			event.setParamMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.isUpdated();
	}

}
