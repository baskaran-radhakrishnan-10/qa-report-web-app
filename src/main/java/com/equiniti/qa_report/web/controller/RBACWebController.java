package com.equiniti.qa_report.web.controller;



import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.RBACController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;

@Controller
@RequestMapping(value="/rbac")
public class RBACWebController {
	
	private static final Logger LOG= Logger.getLogger(RBACWebController.class); 
	
	@Autowired
	private RBACController rbacController;
	
	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public String manageUsersPage(Model model){
		
		return "manage_users_page";
	}
	
	@RequestMapping(value="/getUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUserDetails(){
		LOG.info("Begin: RBACWebController.getUserDetails");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			returnObjMap=rbacController.getUserDetails();			
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: RBACWebController.getUserDetails");
		return returnObjMap;
	}
	
	@RequestMapping(value="/getUniqueUserList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUniqueUserList(){
		LOG.info("Begin :RBACWebController.getUniqueUserList ");
		Map<String,Object> returnObj=new HashMap<String, Object>();
		try {
			returnObj=rbacController.getUniqueUserList();			
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :RBACWebController.getUniqueUserList ");
		return returnObj;
	}
	
	@RequestMapping(value="/addUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addUserDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :RBACWebController.addUserDetails ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=rbacController.addUserDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :RBACWebController.addUserDetails ");
		return returnObj;
	}

	@RequestMapping(value="/updateUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateUserDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin: RBACWebController.updateUserDetails");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=rbacController.updateUserDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: RBACWebController.updateUserDetails");
		return returnObj;
	}
	
	@RequestMapping(value="/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> resetPassword(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :RBACWebController.resetPassword ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=rbacController.resetPassword(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :RBACWebController.resetPassword ");
		return returnObj;
	}
	
	@RequestMapping(value="/getRolesList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRolesList(Map<String,Object> inputParam){
		LOG.info("Begin :RBACWebController.getRolesList ");
		Map<String,Object> returnObj=new HashMap<String, Object>();
		try {
			returnObj=rbacController.getRolesList(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :RBACWebController.getRolesList ");
		return returnObj;
	}
	
	@RequestMapping(value = "/showRoles", method = RequestMethod.GET)
	public String manageRolesPage(Model model){
		return "manage_roles_page";
	}
	
	@RequestMapping(value = "/managePassword", method = RequestMethod.GET)
	public String managePasswordPage(Model model){
		return "manage_password_page";
	}
	
	@RequestMapping(value="/getRoleResourcesInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRoleResourcesInfo(Map<String,Object> inputParam){
		LOG.debug("START getRoleResourcesInfo Method ");
		Map<String,Object> returnObj=new HashMap<String, Object>();
		try {
			returnObj=rbacController.getRoleResourcesInfo(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.debug("END getRoleResourcesInfo Method ");
		return returnObj;
	}

}
