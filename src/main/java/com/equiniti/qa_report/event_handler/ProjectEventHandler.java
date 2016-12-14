package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.ProjectDAO;
import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.event.project.AddProjectEvent;
import com.equiniti.qa_report.event.project.DeleteProjectEvent;
import com.equiniti.qa_report.event.project.GetProjectEvent;
import com.equiniti.qa_report.event.project.UpdateProjectEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class ProjectEventHandler implements IEventHandler<IEvent> {
	
	private static final Logger LOG=Logger.getLogger(ProjectEventHandler.class); 
	private ObjectMapper objMapper=new ObjectMapper();
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
			addProject(eventObj);
		}else if(event instanceof UpdateProjectEvent){
			LOG.debug("Event :" + UpdateProjectEvent.class.getName());
			UpdateProjectEvent eventObj = (UpdateProjectEvent) event;
			updateProject(eventObj);
		}else if (event instanceof DeleteProjectEvent) {
            LOG.debug("Event :" + DeleteProjectEvent.class.getName());
            DeleteProjectEvent eventObj = (DeleteProjectEvent) event;
            deleteData(eventObj);
        }
		LOG.debug("processEvent END");
	}
	
	private void addProject(AddProjectEvent event) throws EventException {
		LOG.info("Begin:ProjectEventHandler.addProject");
		try {
			event.setRowId(projectDAO.addProject(populateProjectEntityFromMap(event.getRequestParam())));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End:ProjectEventHandler.addProject");
	}
	
	private void updateProject(UpdateProjectEvent event) throws EventException {
		try {
			projectDAO.updateProject(populateProjectEntityFromMap(event.getRequestParam()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void getProject(GetProjectEvent event) throws EventException {
		try {
			if(event.isUniqueListRequired()){
				event.setProjectNameList(projectDAO.getUniqueProjectNames());
			}
			else{
				event.setProjectEntityList(projectDAO.getProjectList(event.getRestrictionMap()));
			}
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
    private void deleteData(DeleteProjectEvent event) throws EventException {
    	LOG.debug("Begin: RBACEventHandler.deleteData");
        try {
        	if(null != event.getDeleteKeyList()){
        		projectDAO.deleteData(event.getDeleteKeyList());
        	}
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
        LOG.debug("End: RBACEventHandler.deleteData");
    }
	
	private ProjectEntity populateProjectEntityFromMap(Map<String, Object> mapObject){
		ProjectEntity entity=objMapper.convertValue(mapObject, ProjectEntity.class);
		return entity;
	}

}
