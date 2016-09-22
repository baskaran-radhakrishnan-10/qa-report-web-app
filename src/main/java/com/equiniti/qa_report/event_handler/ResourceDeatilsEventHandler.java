package com.equiniti.qa_report.event_handler;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.ResourceDeatilsDAO;
import com.equiniti.qa_report.event.resource_details.AddResourceDeatilsEvent;
import com.equiniti.qa_report.event.resource_details.GetResourceDeatilsEvent;
import com.equiniti.qa_report.event.resource_details.UpdateResourceDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class ResourceDeatilsEventHandler implements IEventHandler<IEvent> {

	private static final Logger LOG=Logger.getLogger(ResourceDeatilsEventHandler.class); 

	private ResourceDeatilsDAO resourceDeatilsDAO;

	public void setResourceDeatilsDAO(ResourceDeatilsDAO resourceDeatilsDAO) {
		this.resourceDeatilsDAO = resourceDeatilsDAO;
	}

	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetResourceDeatilsEvent) {
			LOG.debug("Event :" + GetResourceDeatilsEvent.class.getName());
			GetResourceDeatilsEvent eventObj = (GetResourceDeatilsEvent) event;
			getResourceDetails(eventObj);
		}else if(event instanceof AddResourceDeatilsEvent){
			LOG.debug("Event :" + AddResourceDeatilsEvent.class.getName());
			AddResourceDeatilsEvent eventObj = (AddResourceDeatilsEvent) event;
			addResourceDetails(eventObj);
		}else if(event instanceof UpdateResourceDeatilsEvent){
			LOG.debug("Event :" + UpdateResourceDeatilsEvent.class.getName());
			UpdateResourceDeatilsEvent eventObj = (UpdateResourceDeatilsEvent) event;
			updateResourceDetails(eventObj);
		}
		LOG.debug("processEvent END");
	}

	private void addResourceDetails(AddResourceDeatilsEvent event) throws EventException {
		try {
			event.setRowId(resourceDeatilsDAO.addResourceDetails(event.getEntity()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void updateResourceDetails(UpdateResourceDeatilsEvent event) throws EventException {
		try {
			resourceDeatilsDAO.updateResourceDetails(event.getEntity());
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void getResourceDetails(GetResourceDeatilsEvent event) throws EventException {
		try {
			if(event.isUniqueListRequired()){
				event.setResourceNameList(resourceDeatilsDAO.getUniqueResourceNames());
			}else if(null != event.getRestrictionMap()){
				event.setResourceEntityList(resourceDeatilsDAO.getResourceDetailsList(event.getRestrictionMap()));
			}
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	

}
