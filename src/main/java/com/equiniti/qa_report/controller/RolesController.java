package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.RolesService;
import com.equiniti.qa_report.util.ApplicationConstants;


@Component("rolesController")
public class RolesController {
	private static final Logger LOG= Logger.getLogger(RolesController.class); 
	@Autowired
	private RolesService rolesService;
	
	public Map<String,Object> getRolesList(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :RolesController.getRolestList ");
		Map<String,Object> returnObjMap=new HashMap<String, Object>();
		try {
			Object returnObj=rolesService.getRolesList();
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
		LOG.info("End :RolesController.getRolestList ");
		return returnObjMap;
	}

}
