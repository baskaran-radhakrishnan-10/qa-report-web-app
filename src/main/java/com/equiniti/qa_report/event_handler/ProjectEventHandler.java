package com.equiniti.qa_report.event_handler;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.ProjectDAO;
import com.equiniti.qa_report.event.project.AddProjectEvent;
import com.equiniti.qa_report.event.project.GetProjectEvent;
import com.equiniti.qa_report.event.project.UpdateProjectEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class ProjectEventHandler implements IEventHandler<IEvent> {
	
	private static final Logger LOG=Logger.getLogger(ProjectEventHandler.class); 
	
	private ProjectDAO projectDAO;

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetProjectEvent) {
			LOG.debug("Event :" + GetProjectEvent.class.getName());
			GetProjectEvent eventObj = (GetProjectEvent) event;
			getProject(eventObj);
		}else if(event instanceof AddProjectEvent){
			LOG.debug("Event :" + AddProjectEvent.class.getName());
			AddProjectEvent eventObj = (AddProjectEvent) event;
			//addProject(eventObj);
		}else if(event instanceof UpdateProjectEvent){
			LOG.debug("Event :" + UpdateProjectEvent.class.getName());
			UpdateProjectEvent eventObj = (UpdateProjectEvent) event;
			//updateProject(eventObj);
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
	
	private void getProject(GetProjectEvent event) throws EventException {
		try {
			if(event.isUniqueListRequired()){
				event.setProjectNameList(projectDAO.getUniqueProjectNames());
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
