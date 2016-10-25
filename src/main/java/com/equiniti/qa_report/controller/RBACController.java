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
import com.equiniti.qa_report.service.api.RBACService;
import com.equiniti.qa_report.util.ApplicationConstants;
@Component("rbacController")
public class RBACController {
	private static final Logger LOG= Logger.getLogger(RBACController.class);
	@Autowired
	private RBACService rbacService;
	
	public Map<String,Object> getUserDetails() throws ControllerException{
		LOG.info("Begin: RBACController.getUserDetails");
		List<User> returnObj= null;
		Map<String,Object> returnObjMap=new HashMap<>();

		try {
			 returnObj=rbacService.getUserDetails();
			 if(null != returnObj){
					returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
					returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
				}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: RBACController.getUserDetails");
		return returnObjMap;
	}
	
	public Map<String,Object> getUniqueUserList() throws ControllerException{
		LOG.info("Begin: RBACController.getUniqueUserList");
		List<String> returnObj= null;
		Map<String,Object> returnObjMap=new HashMap<>();

		try {
			 returnObj=rbacService.getUniqueUserList();
			 if(null != returnObj){
					returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
					returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
				}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: RBACController.getUniqueUserList");
		return returnObjMap;
	}
	
	public Map<String,Object> addUserDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :RBACController.addUserDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=rbacService.addUserDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			
			LOG.info("RBACController.addUserDetails.returnObjMap--> "+ returnObjMap);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :RBACController.addUserDetails ");
		return returnObjMap;
	}
	
	public Map<String,Object> updateUserDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :KTPlanController.updateUserDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=rbacService.updateUserDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean)returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("Begin :KTPlanController.updateUserDetails ");
		return returnObjMap;
	}
/*	public Map<String,Object> resetPassword(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :RBACController.resetPassword");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=rbacService.resetPassword(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			
			LOG.info("RBACController.resetPassword.returnObjMap--> "+ returnObjMap);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :RBACController.resetPassword ");
		return returnObjMap;
	}*/
	
	public Map<String,Object> resetPassword(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :RBACController.resetPassword");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=rbacService.resetPassword(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean)returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :RBACController.resetPassword ");
		return returnObjMap;
	}
	
	public Map<String,Object> getRolesList(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :RolesController.getRolestList ");
		Map<String,Object> returnObjMap=new HashMap<String, Object>();
		try {
			Object returnObj=rbacService.getRolesList();
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.ERROR);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			}
			LOG.info("Begin :RolesController.getRolestList.returnObjMap--> "+returnObjMap);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :RolesController.getRolestList ");
		return returnObjMap;
	}

}
