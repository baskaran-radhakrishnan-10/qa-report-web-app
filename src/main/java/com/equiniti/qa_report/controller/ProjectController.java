package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ProjectService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("projectController")
public class ProjectController {
	
	private static final Logger LOG= Logger.getLogger(ProjectController.class);
	
	@Autowired
	private ProjectService projectService;
	
	public Map<String,Object> getUniqueProjectList(Map<String,Object> inputParam) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<String, Object>();
		try {
			Object returnObj=projectService.getUniqueProjectNameList();
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.ERROR);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> getProjectList(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :ProjectController.getProjectList ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=projectService.getProjectList(inputParam);
			 if(null != returnObj){
					returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
					returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
				}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :ProjectController.getProjectList ");
		return returnObjMap;
	}
	
	public Map<String,Object> addProject(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :ProjectController.addProject ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=projectService.addProject(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :ProjectController.addProject ");
		return returnObjMap;
	}
	
	public Map<String,Object> updateProject(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :ProjectController.updateProject ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=projectService.updateProject(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean) returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :ProjectController.updateProject ");
		return returnObjMap;
	}
	
	public Map<String,Object> deleteData(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			projectService.deleteData(paramMap);
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
}
