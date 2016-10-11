package com.equiniti.qa_report.web.controller;



import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.ManageUsersController;
import com.equiniti.qa_report.controller.RolesController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;

@Controller
@RequestMapping(value="/rbac")
public class RBACWebController {
	
	private static final Logger LOG= Logger.getLogger(RBACWebController.class); 
	
	@Autowired
	private ManageUsersController manageUsersController;
	
	@RequestMapping(value = "/showUser", method = RequestMethod.GET)
	public String manageUsersPage(Model model){
		
		return "manage_users_page";
	}
	
	@RequestMapping(value="/getUserDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUserDetails(){
		LOG.info("Begin: RBACWebController.getUserDetails");
//		List<User> returnObj= null;
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			returnObjMap=manageUsersController.getUserDetails();			
			LOG.info("RBACWebController.getUserDetails.returnObjMap--> "+ returnObjMap);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: RBACWebController.getUserDetails");
		return returnObjMap;
	}
	
	@Autowired
	private RolesController rolesController;

	@RequestMapping(value="/getRolesList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRolesList(Map<String,Object> inputParam){
		LOG.info("Begin :RBACWebController.getRolesList ");
		Map<String,Object> returnObj=new HashMap<String, Object>();
		try {
			returnObj=rolesController.getRolesList(inputParam);
			LOG.info("RBACWebController.getRolesList.returnObjMap--> "+ returnObj);
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

}
