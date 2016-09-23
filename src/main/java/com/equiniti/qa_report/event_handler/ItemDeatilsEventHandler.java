package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.ItemDetailsDAO;
import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.event.item_details.AddItemDeatilsEvent;
import com.equiniti.qa_report.event.item_details.GetItemDeatilsEvent;
import com.equiniti.qa_report.event.item_details.UpdateItemDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class ItemDeatilsEventHandler implements IEventHandler<IEvent> {

	private static final Logger LOG=Logger.getLogger(ItemDeatilsEventHandler.class); 
	
	private ObjectMapper objMapper=new ObjectMapper();

	private ItemDetailsDAO itemDetailsDAO;

	public void setItemDetailsDAO(ItemDetailsDAO itemDetailsDAO) {
		this.itemDetailsDAO = itemDetailsDAO;
	}

	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetItemDeatilsEvent) {
			LOG.debug("Event :" + GetItemDeatilsEvent.class.getName());
			GetItemDeatilsEvent eventObj = (GetItemDeatilsEvent) event;
			getResourceDetails(eventObj);
		}else if(event instanceof AddItemDeatilsEvent){
			LOG.debug("Event :" + AddItemDeatilsEvent.class.getName());
			AddItemDeatilsEvent eventObj = (AddItemDeatilsEvent) event;
			addResourceDetails(eventObj);
		}else if(event instanceof UpdateItemDeatilsEvent){
			LOG.debug("Event :" + UpdateItemDeatilsEvent.class.getName());
			UpdateItemDeatilsEvent eventObj = (UpdateItemDeatilsEvent) event;
			updateResourceDetails(eventObj);
		}
		LOG.debug("processEvent END");
	}

	private void addResourceDetails(AddItemDeatilsEvent event) throws EventException {
		try {
			event.setRowId(itemDetailsDAO.addItemDetails(populateEntityFromMapObject(event.getRequestParam())));
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void updateResourceDetails(UpdateItemDeatilsEvent event) throws EventException {
		try {
			itemDetailsDAO.updateItemDetails(populateEntityFromMapObject(event.getParamMap()));
		} catch (DaoException e) {
			event.setUpdated(false);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			event.setUpdated(false);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void getResourceDetails(GetItemDeatilsEvent event) throws EventException {
		try {
			if(null != event.getRestrictionMap()){
				event.setItemEntityList(itemDetailsDAO.getItemDetailsList(event.getRestrictionMap()));
			}else if(event.isUniqueItemDescReq()){
				event.setItemDescriptionList(itemDetailsDAO.getUniqueItemDescription());
			}
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private ItemEntity populateEntityFromMapObject(Map<String,Object> mapObject){
		ItemEntity entity=objMapper.convertValue(mapObject, ItemEntity.class);
		return entity;
	}

}
