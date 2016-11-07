package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.OperationDAO;
import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.event.remainder_details.AddRemainderDetailsEvent;
import com.equiniti.qa_report.event.remainder_details.GetRemainderDetailsEvent;
import com.equiniti.qa_report.event.remainder_details.UpdateRemainderDetailsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class OperationServiceEventHandler implements IEventHandler<IEvent> {
	
	private static final Logger LOG=Logger.getLogger(OperationServiceEventHandler.class); 
	private ObjectMapper objMapper=new ObjectMapper();
	
	private OperationDAO operationDAO;
	
	public void setOperationDAO(OperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}

	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetRemainderDetailsEvent) {
			LOG.debug("Event :" + GetRemainderDetailsEvent.class.getName());
			GetRemainderDetailsEvent eventObj = (GetRemainderDetailsEvent) event;
			getRemainderDetails(eventObj);
		}
		else if(event instanceof AddRemainderDetailsEvent){
			LOG.debug("Event :" + AddRemainderDetailsEvent.class.getName());
			AddRemainderDetailsEvent eventObj = (AddRemainderDetailsEvent) event;
			addRemainderDetails(eventObj);
		}else if(event instanceof UpdateRemainderDetailsEvent){
			LOG.debug("Event :" + UpdateRemainderDetailsEvent.class.getName());
			UpdateRemainderDetailsEvent eventObj = (UpdateRemainderDetailsEvent) event;
			updateRemainderDetails(eventObj);
		}
		LOG.debug("processEvent END");
		}
	
	public void getRemainderDetails(GetRemainderDetailsEvent event) throws EventException {
		LOG.debug("Begin: OperationServiceEventHandler.getRemainderDetails");
	try {
		event.setRemainderDetailsList(operationDAO.getRemainderDetails(event.getRestrictionMap()));
	} catch (DaoException e) {
		LOG.error("DaoException Occured", e);
		throw new EventException(e.getFaultCode(), e);
	} catch (Exception e) {
		LOG.error("Unknown Exception Occured", e);
		throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
	}
	LOG.debug("End: OperationServiceEventHandler.getRemainderDetails");
	}
	
	private void addRemainderDetails(AddRemainderDetailsEvent event) throws EventException {
		LOG.debug("Begin: OperationServiceEventHandler.addRemainderDetails");
		try {
			event.setRowId(operationDAO.addRemainderDetails(populateEntityFromMapObject(event.getRequestParam())));
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("Begin: OperationServiceEventHandler.addRemainderDetails");
	}
	
	private void updateRemainderDetails(UpdateRemainderDetailsEvent event) throws EventException {
		LOG.debug("Begin: OperationServiceEventHandler.updateRemainderDetails");
		try {
			operationDAO.updateRemainderDetails(populateEntityFromMapObject(event.getRestrictionMap()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: OperationServiceEventHandler.updateRemainderDetails");
	}
	
	
	private Remainder populateEntityFromMapObject(Map<String,Object> mapObject){
		LOG.debug("Begin: OperationServiceEventHandler.populateEntityFromMapObject");
		
		Remainder entity=objMapper.convertValue(mapObject, Remainder.class);
		
		LOG.debug("End: OperationServiceEventHandler.populateEntityFromMapObject.encryptedPassword");
		return entity;
	}
}
