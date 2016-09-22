package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.event.project.AddProjectEvent;
import com.equiniti.qa_report.event.project.GetProjectEvent;
import com.equiniti.qa_report.event.project.UpdateProjectEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ProjectService;

@Service("projectService")
public class ProjectServiceImpl extends BaseAPIImpl implements ProjectService{
	
	@Override
	public List<String> getUniqueProjectNameList() throws APIException {
		GetProjectEvent event=null;
		try{
			event=getEvent(GetProjectEvent.class);
			event.setUniqueListRequired(true);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getProjectNameList();
	}
	
	@Override
	public List<ProjectEntity> getProjectList(Map<String, Object> inputParam) throws APIException {
		GetProjectEvent event=null;
		try{
			event=getEvent(GetProjectEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getProjectEntityList();
	}

	@Override
	public int addProject(Map<String, Object> inputParam) throws APIException {
		AddProjectEvent event=null;
		try{
			event=getEvent(AddProjectEvent.class);
			event.setEntity(populateProjectEntityFromMap(inputParam));
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getRowId();
	}

	@Override
	public void updateProject(Map<String, Object> inputParam) throws APIException {
		UpdateProjectEvent event=null;
		try{
			event=getEvent(UpdateProjectEvent.class);
			event.setEntity(populateProjectEntityFromMap(inputParam));
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
	}
	
	private ProjectEntity populateProjectEntityFromMap(Map<String, Object> inputParam){
		
		ProjectEntity entity=new ProjectEntity();
		
		return entity;
		
	}

}
