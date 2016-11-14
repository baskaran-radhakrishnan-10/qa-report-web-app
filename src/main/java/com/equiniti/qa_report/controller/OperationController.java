package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.OperationService;
import com.equiniti.qa_report.service.api.ProjectService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("operationController")
public class OperationController {
	
	private static final Logger LOG= Logger.getLogger(OperationController.class); 
	
	@Autowired
	public  OperationService operationService;
	
	@Autowired
	public ProjectService projectService;
	
	public Map<String,Object> getProjectList(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :OperationController.getProjectList ");
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
		LOG.info("End :OperationController.getProjectList ");
		return returnObjMap;
	}
	
	public Map<String,Object> addProject(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :OperationController.addProject ");
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
		LOG.info("End :OperationController.addProject ");
		return returnObjMap;
	}
	
	public Map<String,Object> updateProject(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :OperationController.updateProject ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=projectService.updateProject(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean) returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :OperationController.updateProject ");
		return returnObjMap;
	}
	
	public Map<String,Object> getRemainderDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :OperationController.getRemainderDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=operationService.getRemainderDetails();
			 if(null != returnObj){
					returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
					returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
				}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :OperationController.getRemainderDetails ");
		return returnObjMap;
	}
	
	public Map<String,Object> addRemainderDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :OperationController.addRemainderDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=operationService.addRemainderDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :OperationController.addRemainderDetails ");
		return returnObjMap;
	}
	
	public Map<String,Object> updateRemainderDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :OperationController.updateRemainderDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=operationService.updateRemainderDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean)returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :OperationController.updateRemainderDetails ");
		return returnObjMap;
	}

}
