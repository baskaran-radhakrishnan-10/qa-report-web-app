package com.equiniti.qa_report.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ManageUsersService;
import com.equiniti.qa_report.util.ApplicationConstants;



@Component("manageUsersController")
public class ManageUsersController {

	@Autowired
	private ManageUsersService manageUsersService;
	
	private static final Logger LOG= Logger.getLogger(ManageUsersController.class); 
	
	public Map<String,Object> getUserDetails() throws ControllerException{
		LOG.info("Begin: ManageUsersController.getUserDetails");
		List<User> returnObj= null;
		Map<String,Object> returnObjMap=new HashMap<>();

		try {
			 returnObj=manageUsersService.getUserDetails();
			 if(null != returnObj){
					returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
					returnObjMap.put(ApplicationConstants.USER_DATA, returnObj);
				}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: ManageUsersController.getUserDetails");
		return returnObjMap;
	}
}
