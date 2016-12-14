package com.equiniti.qa_report.service.api.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.entity.RolesResources;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.login.DoLoginEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.form.model.LoginModelAttribute;
import com.equiniti.qa_report.service.api.LoginService;
import com.equiniti.qa_report.service.api.RoleResourceService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Service("loginService")
public class LoginServiceImpl extends BaseAPIImpl implements LoginService{

	private static final Logger LOG=Logger.getLogger(LoginServiceImpl.class); 

	@Autowired
	private HttpSession session;

	@Autowired
	private RoleResourceService roleResourceService;
	
	private CacheInstance CACHE_INS;

	@Override
	public LoginModelAttribute doLogin(LoginModelAttribute modelAttribute) throws APIException{
		
		LOG.debug("START doLogin Method");
		
		DoLoginEvent event = getEvent(DoLoginEvent.class);
		
		try {
			
			CACHE_INS = CacheInstance.getInstance();
			
			event.setUserId(modelAttribute.getUserId().trim().toLowerCase());
			event.setPassword(modelAttribute.getPassword());
			
			processEvent(event);
			
			User user=event.getUser();
			
			if(null == user){
				modelAttribute.getModel().addAttribute("error", "Invalid user-id or password.");
				modelAttribute.setSuccess(false);
				modelAttribute.setResultMapping(ApplicationConstants.LOGIN_PAGE);
				return modelAttribute;
			}
			
			Roles role=user.getRoleId();
			
			if(null == role){
				modelAttribute.getModel().addAttribute("error", "Invalid Role Permission Assigned.");
				modelAttribute.setSuccess(false);
				modelAttribute.setResultMapping(ApplicationConstants.LOGIN_PAGE);
				return modelAttribute;
			}
			
			/*if(user.isFirstLogin()){
				modelAttribute.setSuccess(true);
				modelAttribute.setResultMapping(ApplicationConstants.RESET_PASSWORD_PAGE);
				return modelAttribute;
			}*/
			
			List<RolesResources> roleResourceList=roleResourceService.getRolesResourcesByRoleId(role.getGkey());

			session.setAttribute(ApplicationConstants.USER_OBJ, user);
			
			session.setAttribute(ApplicationConstants.USER_ID, user.getUserId());
			
			session.setAttribute(ApplicationConstants.USER_NAME, user.getUserFullName());
			
			session.setAttribute(ApplicationConstants.IS_LOGGED_IN, true);
			
			session.setAttribute(ApplicationConstants.ROLE_ID, role.getRoleName());
			
			session.setAttribute(ApplicationConstants.FIRST_LOGIN, user.isFirstLogin());
			
			CACHE_INS.removeAllItemFromGroup(user.getUserId());
			
			CACHE_INS.putItemInCache(ApplicationConstants.ROLE_RESOURCE_INFO, roleResourceList, user.getUserId());
			
			LOG.debug("END doLogin Method");
			
		}catch (EventException e) {
			throw new APIException(e.getFaultCode(), e);
		} catch (CacheException e) {
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return modelAttribute;
	}
	
}
