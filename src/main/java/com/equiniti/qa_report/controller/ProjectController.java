package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ProjectService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("projectController")
public class ProjectController {
	
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

}
