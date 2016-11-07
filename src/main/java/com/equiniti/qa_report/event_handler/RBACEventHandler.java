package com.equiniti.qa_report.event_handler;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.equiniti.qa_report.dao.api.RBACDAO;
import com.equiniti.qa_report.dao.api.RBACRolesDAO;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.rbac.AddUserDeatilsEvent;
import com.equiniti.qa_report.event.rbac.GetUniqueUserListEvent;
import com.equiniti.qa_report.event.rbac.ResetPasswordEvent;
import com.equiniti.qa_report.event.rbac.UpdateUserDetailsEvent;
import com.equiniti.qa_report.event.roles.GetRolesEvent;
import com.equiniti.qa_report.event.user_details.GetUserDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.exception.SecurityException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.exception.api.faultcode.SecurityFaultCodes;
import com.equiniti.qa_report.security.crypto.EncryptionDecryption;
import com.equiniti.qa_report.util.ApplicationConstants;

public class RBACEventHandler implements IEventHandler<IEvent> {
	
	private static final Logger LOG=Logger.getLogger(RBACEventHandler.class); 
	private ObjectMapper objMapper=new ObjectMapper();
	private EncryptionDecryption cryptoService;
	private RBACDAO rbacDAO;
	private RBACRolesDAO rbacRolesDAO;


	public void setRbacRolesDAO(RBACRolesDAO rbacRolesDAO) {
		this.rbacRolesDAO = rbacRolesDAO;
	}

	public void setRbacDAO(RBACDAO rbacDAO) {
		this.rbacDAO = rbacDAO;
	}
	
	  public void setCryptoService(EncryptionDecryption cryptoService) {
		this.cryptoService = cryptoService;
	}
	
	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetUserDeatilsEvent) {
			LOG.debug("Event :" + GetUserDeatilsEvent.class.getName());
			GetUserDeatilsEvent eventObj = (GetUserDeatilsEvent) event;
			getUserDetails(eventObj);
		}
		else if(event instanceof AddUserDeatilsEvent){
			LOG.debug("Event :" + AddUserDeatilsEvent.class.getName());
			AddUserDeatilsEvent eventObj = (AddUserDeatilsEvent) event;
			addUserDetails(eventObj);
		}else if(event instanceof UpdateUserDetailsEvent){
			LOG.debug("Event :" + UpdateUserDetailsEvent.class.getName());
			UpdateUserDetailsEvent eventObj = (UpdateUserDetailsEvent) event;
			updateUserDetails(eventObj);
		}
		else if(event instanceof GetRolesEvent){
			LOG.debug("Event :" + GetRolesEvent.class.getName());
			GetRolesEvent eventObj = (GetRolesEvent) event;
			getRoles(eventObj);
		}
		else if(event instanceof GetUniqueUserListEvent){
			LOG.debug("Event :" + GetUniqueUserListEvent.class.getName());
			GetUniqueUserListEvent eventObj = (GetUniqueUserListEvent) event;
			getUniqueUserList(eventObj);
		}
		else if(event instanceof ResetPasswordEvent){
			LOG.debug("Event :" + ResetPasswordEvent.class.getName());
			ResetPasswordEvent eventObj = (ResetPasswordEvent) event;
			resetPassword(eventObj);
		}
		LOG.debug("processEvent END");
		}
		

	public void getUserDetails(GetUserDeatilsEvent event) throws EventException {
		LOG.debug("Begin: RBACEventHandler.getUserDetails");
	try {
		event.setUserDetailsList(rbacDAO.getUserDetails(event.getRestrictionMap()));
	} catch (DaoException e) {
		LOG.error("DaoException Occured", e);
		throw new EventException(e.getFaultCode(), e);
	} catch (Exception e) {
		LOG.error("Unknown Exception Occured", e);
		throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
	}
	LOG.debug("End: RBACEventHandler.getUserDetails");
	}
	public void getUniqueUserList(GetUniqueUserListEvent event) throws EventException {
		LOG.debug("Begin: RBACEventHandler.getUniqueUserList");
	try {
		event.setUniqueUserList(rbacDAO.getUniqueUserList());
	} catch (DaoException e) {
		LOG.error("DaoException Occured", e);
		throw new EventException(e.getFaultCode(), e);
	} catch (Exception e) {
		LOG.error("Unknown Exception Occured", e);
		throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
	}
	LOG.debug("End: RBACEventHandler.getUniqueUserList");
}
	
	private void addUserDetails(AddUserDeatilsEvent event) throws EventException {
		LOG.debug("Begin: RBACEventHandler.addUserDetails");
		try {
			event.setRowId(rbacDAO.addUserDetails(populateEntityFromMapObjectForAddUser(event.getRequestParam())));
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("Begin: RBACEventHandler.addUserDetails");
	}
	
	private void resetPassword(ResetPasswordEvent event) throws EventException {
		LOG.debug("Begin: RBACEventHandler.resetPassword");
		try {
			rbacDAO.resetPassword(populateEntityFromMapObjectResetPassword(event.getParamMap()));
		} catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("Begin: RBACEventHandler.resetPassword");
	}
	private void updateUserDetails(UpdateUserDetailsEvent event) throws EventException {
		LOG.debug("Begin: RBACEventHandler.updateUserDetails");
		try {
			rbacDAO.updateUserDetails(populateEntityFromMapObject(event.getRestrictionMap()));
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACEventHandler.updateUserDetails");
	}
	
	private void getRoles(GetRolesEvent event) throws EventException {
		LOG.debug("Begin: RBACEventHandler.getRoles");
		try {
			if(event.isUniqueListRequired()){
				event.setRolesList(rbacRolesDAO.getRolesNameList());
			}else if(event.isListAll()){
				event.setEntityList(rbacRolesDAO.getRolesList());
			}
		} catch (DaoException e) {
			LOG.error("DaoException Occured", e);
			throw new EventException(e.getFaultCode(), e);
		} catch (Exception e) {
			LOG.error("Unknown Exception Occured", e);
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACEventHandler.getRoles");
	}
	
	private User populateEntityFromMapObjectForAddUser(Map<String,Object> mapObject) throws EventException{
		LOG.debug("Begin: RBACEventHandler.populateEntityFromMapObject");
		String encryptedPassword;
		User entity=objMapper.convertValue(mapObject, User.class);
		try{
			encryptedPassword=cryptoService.encrypt(ApplicationConstants.DEFAULT_LOGIN_PASSWORD);
		}
		catch(SecurityException se){
			throw new EventException(SecurityFaultCodes.SECURITY_CRYPTO_ENCRIPTION_FAILED_ERROR,se);			
		}
		catch(Exception e){
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		entity.setPassword(encryptedPassword);
		entity.setFirstLogin(true);
	
		LOG.debug("End: RBACEventHandler.populateEntityFromMapObject.encryptedPassword");
		return entity;
	}
	
	private User populateEntityFromMapObjectResetPassword(Map<String,Object> mapObject) throws EventException{
		LOG.debug("Begin: RBACEventHandler.populateEntityFromMapObjectResetPassword");
		String encryptedPassword;
		User entity=objMapper.convertValue(mapObject, User.class);
		try{
			encryptedPassword=cryptoService.encrypt(entity.getPassword());
		}
		catch(SecurityException se){
			throw new EventException(SecurityFaultCodes.SECURITY_CRYPTO_ENCRIPTION_FAILED_ERROR,se);			
		}
		catch(Exception e){
			throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		entity.setPassword(encryptedPassword);
		entity.setFirstLogin(true);
	
		LOG.debug("End: RBACEventHandler.populateEntityFromMapObjectResetPassword.encryptedPassword");
		return entity;
	}
	
	private User populateEntityFromMapObject(Map<String,Object> mapObject){
		LOG.debug("Begin: RBACEventHandler.populateEntityFromMapObject");
		
		User entity=objMapper.convertValue(mapObject, User.class);
		
		LOG.debug("End: RBACEventHandler.populateEntityFromMapObject.encryptedPassword");
		return entity;
	}
	
}
