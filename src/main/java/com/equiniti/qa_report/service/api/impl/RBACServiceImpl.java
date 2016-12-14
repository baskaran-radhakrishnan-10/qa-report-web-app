package com.equiniti.qa_report.service.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.rbac.AddUserDeatilsEvent;
import com.equiniti.qa_report.event.rbac.DeleteUserDetailsEvent;
import com.equiniti.qa_report.event.rbac.GetUniqueUserListEvent;
import com.equiniti.qa_report.event.rbac.ResetPasswordEvent;
import com.equiniti.qa_report.event.rbac.UpdateUserDetailsEvent;
import com.equiniti.qa_report.event.roles.GetRolesEvent;
import com.equiniti.qa_report.event.user_details.GetUserDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.RBACService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Service("rbacService")
public class RBACServiceImpl extends BaseAPIImpl implements RBACService {

	private static final Logger LOG= Logger.getLogger(RBACServiceImpl.class); 
	
	private CacheInstance CACHE_INS;
	
	@Autowired
	private HttpSession session;

	@Override
	public int addUserDetails(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :RBACServiceImpl.addUserDetails ");
		AddUserDeatilsEvent event=null;
		try{
			event=getEvent(AddUserDeatilsEvent.class);
			event.setRequestParam(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :RBACServiceImpl.addUserDetails ");
		return event.getRowId();
	}

	@Override
	public List<User> getUserDetails() throws APIException {
		LOG.info("Begin: RBACServiceImpl.getUserDetails");
		GetUserDeatilsEvent event=null;
		Map<String,Object> restrictionMap = new HashMap<String, Object>();
		try{
			event=getEvent(GetUserDeatilsEvent.class);
			event.setEntity(populateUserDetailsEntityFromList());
			restrictionMap.put("deleted", false);
			event.setRestrictionMap(restrictionMap);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End: RBACServiceImpl.getUserDetails");
		return event.getUserDetailsList();
	}

	@Override
	public List<String> getUniqueUserList() throws APIException {
		LOG.info("Begin: RBACServiceImpl.getUniqueUserList");
		GetUniqueUserListEvent event=null;
		try{
			event=getEvent(GetUniqueUserListEvent.class);
			//			event.setUniqueListRequired(true);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End: RBACServiceImpl.getUniqueUserList");
		return event.getUniqueUserList();
	}

	@Override
	public boolean resetPassword(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin: RBACServiceImpl.resetPassword");
		ResetPasswordEvent event=null;
		try{
			event=getEvent(ResetPasswordEvent.class);
			event.setParamMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End: RBACServiceImpl.resetPassword");
		return event.isUpdated();
	}

	@Override
	public boolean updateUserDetails(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :RBACServiceImpl.updateUserDetails ");
		UpdateUserDetailsEvent event=null;
		try{
			event=getEvent(UpdateUserDetailsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :RBACServiceImpl.updateUserDetails ");
		return event.isUpdated();
	}
	
	@Override
	public List<Roles> getRolesList() throws APIException {
		LOG.info("Begin: RBACServiceImpl.getRolesList");
		GetRolesEvent event=null;
		try{
			event=getEvent(GetRolesEvent.class);
			event.setListAll(true);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End: RBACServiceImpl.getRolesList");
		return event.getEntityList();
	}
	
	@Override
	public Map<String,Object> getRoleResourcesInfo() throws APIException {
		Map<String,Object> returnObj=new HashMap<String, Object>();
		try {
			CACHE_INS=CacheInstance.getInstance();
			returnObj.put(ApplicationConstants.ROLE_RESOURCE_INFO, CACHE_INS.getItemFromCache(ApplicationConstants.ROLE_RESOURCE_INFO, (String)session.getAttribute(ApplicationConstants.USER_ID)));
			returnObj.put(ApplicationConstants.BASE_URL, (String)session.getAttribute(ApplicationConstants.BASE_URL));
		} catch (CacheException e) {
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		} catch(Exception e){
			if(e instanceof IllegalArgumentException){
				if(e.getMessage().indexOf("groupName must not be null") != -1){
					throw new APIException(CommonFaultCode.CACHE_FAILED_ERROR,e);
				}
			}
		}
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteData(Map<String, Object> paramMap) throws APIException {
		LOG.info("Begin: RBACServiceImpl.deleteData");
		DeleteUserDetailsEvent event=null;
		try{
			if(paramMap.containsKey("deleteRecordList")){
				event=getEvent(DeleteUserDetailsEvent.class);
				event.setDeleteKeyList((List<Integer>) paramMap.get("deleteRecordList"));
			}
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: RBACServiceImpl.deleteData");
	}
	

	private User populateUserDetailsEntityFromList(){

		User entity=new User();

		return entity;

	}
}
