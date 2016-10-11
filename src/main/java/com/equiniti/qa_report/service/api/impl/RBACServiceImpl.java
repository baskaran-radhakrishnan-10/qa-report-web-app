package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.rbac.AddUserDeatilsEvent;
import com.equiniti.qa_report.event.rbac.GetUniqueUserListEvent;
import com.equiniti.qa_report.event.rbac.ResetPasswordEvent;
import com.equiniti.qa_report.event.roles.GetRolesEvent;
import com.equiniti.qa_report.event.user_details.GetUserDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.RBACService;

@Service("rbacService")
public class RBACServiceImpl extends BaseAPIImpl implements RBACService {
	
	private static final Logger LOG= Logger.getLogger(RBACServiceImpl.class); 
	
	/*private EncryptionDecryption cryptoService;
	
	  public void setCryptoService(EncryptionDecryption cryptoService) {
			this.cryptoService = cryptoService;
		}*/
	
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
		try{
			event=getEvent(GetUserDeatilsEvent.class);
			event.setEntity(populateUserDetailsEntityFromList());
			LOG.info("event--> "+event);
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
	public List<Roles> getRolesList() throws APIException {
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
		return event.getEntityList();
	}
	
private User populateUserDetailsEntityFromList(){
		
	User entity=new User();
		
		return entity;
		
	}
}
