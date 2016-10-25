package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.KTPlanDAO;
import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.event.kt_plan.AddKTPlanDetailsEvent;
import com.equiniti.qa_report.event.kt_plan.GetKTPlanDetailsEvent;
import com.equiniti.qa_report.event.kt_plan.UpdateKTPlanDetailsEvent;
import com.equiniti.qa_report.event.resource_details.UpdateResourceDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class KTPlanEventHandler implements IEventHandler<IEvent> {
	private static final Logger LOG=Logger.getLogger(KTPlanEventHandler.class); 
	private ObjectMapper objMapper=new ObjectMapper();
	private KTPlanDAO ktPlanDAO;
	
	public void setKtPlanDAO(KTPlanDAO ktPlanDAO) {
		this.ktPlanDAO = ktPlanDAO;
	}

	@Override
	public void processEvent(IEvent event) throws EventException {
		
		LOG.debug("processEvent START");
		if (event instanceof GetKTPlanDetailsEvent) {
			LOG.debug("Event :" + GetKTPlanDetailsEvent.class.getName());
			GetKTPlanDetailsEvent eventObj = (GetKTPlanDetailsEvent) event;
			getKTPlanDetails(eventObj);
		}else if(event instanceof AddKTPlanDetailsEvent){
			LOG.debug("Event :" + AddKTPlanDetailsEvent.class.getName());
			AddKTPlanDetailsEvent eventObj = (AddKTPlanDetailsEvent) event;
			addKTDetails(eventObj);
		}else if(event instanceof UpdateKTPlanDetailsEvent){
			LOG.debug("Event :" + UpdateKTPlanDetailsEvent.class.getName());
			UpdateKTPlanDetailsEvent eventObj = (UpdateKTPlanDetailsEvent) event;
			updateKTDetails(eventObj);
		}
		LOG.debug("processEvent END");
		}
	
	public void getKTPlanDetails(GetKTPlanDetailsEvent event) throws EventException {
		LOG.debug("Begin: KTPlanEventHandler.getKTPlanDetails");
	try {
		event.setKtPlansList(ktPlanDAO.getKTPlanDetails(event.getRestrictionMap()));
	} catch (DaoException e) {
		LOG.error("DaoException Occured", e);
		throw new EventException(e.getFaultCode(), e);
	} catch (Exception e) {
		LOG.error("Unknown Exception Occured", e);
		throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
	}
	LOG.debug("End: KTPlanEventHandler.getKTPlanDetails");
	}
	private void addKTDetails(AddKTPlanDetailsEvent event) throws EventException {
		LOG.debug("Begin: KTPlanEventHandler.addKTDetails");
		try {
			event.setRowId(ktPlanDAO.addKTDetails(populateEntityFromMapObject(event.getRequestParam())));
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: KTPlanEventHandler.addKTDetails");
	}
	
	private void updateKTDetails(UpdateKTPlanDetailsEvent event) throws EventException {
		LOG.debug("Begin: KTPlanEventHandler.updateKTDetails");
		try {
			ktPlanDAO.updateKTDetails(populateEntityFromMapObject(event.getRestrictionMap()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: KTPlanEventHandler.updateKTDetails");
	}
	
	private KTPlan populateEntityFromMapObject(Map<String,Object> mapObject) throws EventException{
		LOG.debug("Begin: KTPlanEventHandler.populateEntityFromMapObject");
		KTPlan entity=objMapper.convertValue(mapObject, KTPlan.class);
		LOG.debug("End: KTPlanEventHandler.populateEntityFromMapObject");
		return entity;
	}
}
