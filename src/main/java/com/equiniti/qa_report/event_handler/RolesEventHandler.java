package com.equiniti.qa_report.event_handler;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.RolesDAO;
import com.equiniti.qa_report.event.roles.GetRolesEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class RolesEventHandler implements IEventHandler<IEvent> {
	
	private static final Logger LOG=Logger.getLogger(RolesEventHandler.class); 
	
	private RolesDAO rolesDAO;
	
	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}

	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetRolesEvent) {
			LOG.debug("Event :" + GetRolesEvent.class.getName());
			GetRolesEvent eventObj = (GetRolesEvent) event;
			getProject(eventObj);
		}
		LOG.debug("processEvent END");
	}
	
	/*private void addProject(AddProjectEvent event) throws EventException {
		try {
			event.setRowId(projectDAO.addProject(event.getEntity()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void updateProject(UpdateProjectEvent event) throws EventException {
		try {
			projectDAO.updateProject(event.getEntity());
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}*/
	
	private void getProject(GetRolesEvent event) throws EventException {
		try {
			if(event.isUniqueListRequired()){
				event.setRolesList(rolesDAO.getRolesList());
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
