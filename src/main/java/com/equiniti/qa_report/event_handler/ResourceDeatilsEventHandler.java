package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.ResourceDeatilsDAO;
import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.event.resource_details.AddResourceDeatilsEvent;
import com.equiniti.qa_report.event.resource_details.DeleteResourceDetailsEvent;
import com.equiniti.qa_report.event.resource_details.GetResourceDeatilsEvent;
import com.equiniti.qa_report.event.resource_details.UpdateResourceDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.util.CommonUtil;

public class ResourceDeatilsEventHandler implements IEventHandler<IEvent> {

	private static final Logger LOG=Logger.getLogger(ResourceDeatilsEventHandler.class); 
	
	private ObjectMapper objMapper=new ObjectMapper();

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
		}else if (event instanceof DeleteResourceDetailsEvent) {
            LOG.debug("Event :" + DeleteResourceDetailsEvent.class.getName());
            DeleteResourceDetailsEvent eventObj = (DeleteResourceDetailsEvent) event;
            deleteResourceDetails(eventObj);
        }else {
            LOG.debug("Unknow Event");
        }
		LOG.debug("processEvent END");
	}

	private void addResourceDetails(AddResourceDeatilsEvent event) throws EventException {
		try {
			event.setRowId(resourceDeatilsDAO.addResourceDetails(populateEntityFromMapObject(event.getRestrictionMap())));
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
			resourceDeatilsDAO.updateResourceDetails(populateEntityFromMapObject(event.getRestrictionMap()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void deleteResourceDetails(DeleteResourceDetailsEvent event) throws EventException {
        try {
        	resourceDeatilsDAO.deleteResourceDetails(event.getgKey());
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
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
	
	private ResourceEntity populateEntityFromMapObject(Map<String,Object> mapObject){
		ResourceEntity entity=objMapper.convertValue(CommonUtil.removeTransientObject(mapObject), ResourceEntity.class);
		return entity;
	}

}
