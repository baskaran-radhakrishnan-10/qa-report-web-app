package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.controller.OperationController;
import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.event.project.AddProjectEvent;
import com.equiniti.qa_report.event.project.DeleteProjectEvent;
import com.equiniti.qa_report.event.project.GetProjectEvent;
import com.equiniti.qa_report.event.project.UpdateProjectEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ProjectService;

@Service("projectService")
public class ProjectServiceImpl extends BaseAPIImpl implements ProjectService{
	private static final Logger LOG= Logger.getLogger(OperationController.class);
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
		LOG.info("Begin :ProjectServiceImpl.getProjectList");
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
		LOG.info("End :ProjectServiceImpl.getProjectList");
		return event.getProjectEntityList();
	}

	@Override
	public int addProject(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :ProjectServiceImpl.addProject");
		AddProjectEvent event=null;
		try{
			event=getEvent(AddProjectEvent.class);
			event.setRequestParam(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :ProjectServiceImpl.addProject");
		return event.getRowId();
	}

	@Override
	public boolean updateProject(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :ProjectServiceImpl.updateProject");
		UpdateProjectEvent event=null;
		try{
			event=getEvent(UpdateProjectEvent.class);
			event.setRequestParam(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :ProjectServiceImpl.updateProject");
		return event.isUpdated();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteData(Map<String, Object> paramMap) throws APIException {
		LOG.info("Begin: ProjectServiceImpl.deleteData");
		DeleteProjectEvent event=null;
		try{
			if(paramMap.containsKey("deleteRecordList")){
				event=getEvent(DeleteProjectEvent.class);
				event.setDeleteKeyList((List<Integer>) paramMap.get("deleteRecordList"));
			}
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: ProjectServiceImpl.deleteData");
	}

}
