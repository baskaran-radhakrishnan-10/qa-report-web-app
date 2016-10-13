package com.equiniti.qa_report.service.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.Resources;
import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.entity.RolesResources;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.login.DoLoginEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.util.CollectionUtil;
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

	@Override
	public LoginModelAttribute doLogin(LoginModelAttribute modelAttribute) throws APIException{
		
		LOG.debug("START doLogin Method");
		
		DoLoginEvent event = getEvent(DoLoginEvent.class);
		
		try {
			
			event.setUserId(modelAttribute.getUserId());
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
			
			List<RolesResources> roleResourceList=roleResourceService.getRolesResourcesByRoleId(role.getGkey());

			/*if(null == roleResourceList || roleResourceList.isEmpty()){
				modelAttribute.getModel().addAttribute("error", "Invalid Resource Permission Assigned.");
				modelAttribute.setSuccess(false);
				modelAttribute.setResultMapping(ApplicationConstants.LOGIN_PAGE);
				return modelAttribute;
			}*/
			
			Map<String, Object> resourceMap=new HashMap<>();
			
			Map<String, String> htmlOutputMap=new HashMap<>();

			for(RolesResources roleResource : roleResourceList){
				Resources resourcesEntity=roleResource.getResourceId();
				String resourceUrl=resourcesEntity.getResourceUrl();
				String resourceModule=resourcesEntity.getResourceModule();
				String resourceName=resourcesEntity.getResourceName();
				String resourceModuleIcon=resourcesEntity.getResourceModuleIcon();
				Map<String,String> innerMap=new HashMap<>();
				innerMap.put("resourceUrl", resourceUrl);
				innerMap.put("resourceName", resourceName);
				CollectionUtil.fillListData(resourceMap, resourceModule+"~~"+resourceModuleIcon, innerMap);
			}
			
			constructLIHtml(htmlOutputMap, resourceMap);
			
			session.setAttribute(ApplicationConstants.RESOURCE_MAP, htmlOutputMap);
			
			session.setAttribute(ApplicationConstants.USER_OBJ, user);
			
			session.setAttribute(ApplicationConstants.USER_ID, user.getUserId());
			
			session.setAttribute(ApplicationConstants.IS_LOGGED_IN, true);
			
			LOG.debug("END doLogin Method");
			
		}catch (EventException e) {
			throw new APIException(e.getFaultCode(), e);
		}
		return modelAttribute;
	}
	
	@SuppressWarnings("unchecked")
	private void constructLIHtml(Map<String,String> htmlOutputMap,Map<String,Object> resourceMap){
		Set<String> keySet=resourceMap.keySet();
		for(String key : keySet){
			StringBuffer htmlBuffer=new StringBuffer();
			String resourceModule=key.split("~~")[0];
			String resourceModuleIcon=key.split("~~")[1];
			List<Map<String,Object>> innerList=(List<Map<String, Object>>) resourceMap.get(key);
			
			if(innerList.size() > 1){
				htmlBuffer.append("<li id=\""+resourceModule+"\" class=\"ripple\"><a class=\"tree-toggle nav-header\"> <span class=\""+resourceModuleIcon+" fa\"></span>Operations<span class=\"fa-angle-right fa right-arrow text-right\"></span></a>");
				htmlBuffer.append("<ul class=\"nav nav-list tree\">");
				for(Map<String,Object> innerMap : innerList){
					String resourceUrl=(String) innerMap.get("resourceUrl");
					String resourceName=(String) innerMap.get("resourceName");
					htmlBuffer.append("<li><a href=\""+resourceUrl+"\">"+resourceName+"</a></li>");
				}
				htmlBuffer.append("</ul>");
				htmlBuffer.append("</li>");
			}else{
				htmlBuffer.append("<li id=\""+resourceModule+"\"><a href=\""+innerList.get(0).get("resourceUrl")+"\"><span class=\""+resourceModuleIcon+" fa\"></span>"+innerList.get(0).get("resourceName")+"</a></li>");
			}
			htmlOutputMap.put(resourceModule, htmlBuffer.toString());
			System.out.println(htmlBuffer.toString());
		}
	}
	
}
