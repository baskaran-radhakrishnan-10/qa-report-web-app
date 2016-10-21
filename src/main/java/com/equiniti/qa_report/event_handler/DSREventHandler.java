package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.DSRDAO;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.event.dsr.AddDSREvent;
import com.equiniti.qa_report.event.dsr.GetDSREvent;
import com.equiniti.qa_report.event.dsr.UpdateDSREvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class DSREventHandler implements IEventHandler<IEvent> {

    private static final Logger LOG = Logger.getLogger(DSREventHandler.class.getName());
    
    private ObjectMapper objMapper=new ObjectMapper();
    
    private DSRDAO dsrDAO;

	public void setDsrDAO(DSRDAO dsrDAO) {
		this.dsrDAO = dsrDAO;
	}

	public void processEvent(IEvent event) throws EventException {
        LOG.debug("processEvent START");
        if (event instanceof AddDSREvent) {
            LOG.debug("Event :" + AddDSREvent.class.getName());
            AddDSREvent eventObj = (AddDSREvent) event;
            addDSR(eventObj);
        }else if (event instanceof UpdateDSREvent) {
            LOG.debug("Event :" + UpdateDSREvent.class.getName());
            UpdateDSREvent eventObj = (UpdateDSREvent) event;
            updateDSR(eventObj);
        }else if (event instanceof GetDSREvent) {
            LOG.debug("Event :" + GetDSREvent.class.getName());
            GetDSREvent eventObj = (GetDSREvent) event;
            getDSR(eventObj);
        }else {
            LOG.debug("Unknow Event");
        }
        LOG.debug("processEvent END");
    }

    private void addDSR(AddDSREvent event) throws EventException {
        try {
        	event.setAddedRow(dsrDAO.addDSREntity(populateEntityFromMap(event.getParamMap())));
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }

    private void updateDSR(UpdateDSREvent event) throws EventException {
        try {
        	dsrDAO.updateDSREntity(populateEntityFromMap(event.getParamMap()));
        } catch (DaoException e) {
        	event.setUpdated(false);
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
        	event.setUpdated(false);
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }

    private void getDSR(GetDSREvent event) throws EventException {
        try {
            if (event.isListAll() || (null != event.getRestrictionMap())) {
                event.setDSREntityList(dsrDAO.getDSREntityList(event.getRestrictionMap()));
            } else if (null != event.getRestrictionMap()) {
                event.setDSREntityList(dsrDAO.getDSREntityList(event.getRestrictionMap()));
            } 
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }
    
    private DSREntity populateEntityFromMap(Map<String,Object> mapObject){
    	DSREntity entity=objMapper.convertValue(mapObject, DSREntity.class);
    	return entity;
    }
    
}
