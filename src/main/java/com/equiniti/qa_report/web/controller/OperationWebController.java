package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.OperationController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;
import com.equiniti.qa_report.util.ApplicationConstants;

@Controller
@RequestMapping(value="operation")
public class OperationWebController {
	@Autowired
	private OperationController operationController;

	private static final Logger LOG= Logger.getLogger(RBACWebController.class); 
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/add_user" , method = RequestMethod.GET)
	public String showAddUserPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Add New User");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/add_project" , method = RequestMethod.GET)
	public String showAddProjectPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Add New Project");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/edit_user" , method = RequestMethod.GET)
	public String showEditUserPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Edit User Details");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/edit_project" , method = RequestMethod.GET)
	public String showEditProjectPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Edit Project Details");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/add_leave" , method = RequestMethod.GET)
	public String showAddLeavePage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Add Leave Details");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/add_permission" , method = RequestMethod.GET)
	public String showAddPremissionPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Add Permission Details");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/add_leave_plans" , method = RequestMethod.GET)
	public String showAddLeavePlansPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Add Leave Plans");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/manage_projects" , method = RequestMethod.GET)
	public String showManageProjectsPage(Model model) throws UIException{
	//session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Manage Projects");
		return "manage_projects_page";
	}
	
	@RequestMapping(value="/getProjectList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProjectList(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :OperationWebController.getProjectList ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=operationController.getProjectList(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :OperationWebController.getProjectList ");
		return returnObj;
	}
	
	@RequestMapping(value="/addProject", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addProject(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin:OperationWebController.addProject");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=operationController.addProject(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End:OperationWebController.addProject");
		return returnObj;
	}
	
	@RequestMapping(value="/updateProject", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateProject(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :OperationWebController.updateProject ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=operationController.updateProject(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :OperationWebController.updateProject ");
		return returnObj;
	}
	
	@RequestMapping(value = "/remainder_settings" , method = RequestMethod.GET)
	public String showRemainderSettingsPage(Model model) throws UIException{
		//session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Remainder Settings");
		return "remainder_settings_page";
	}
	
	@RequestMapping(value="/getRemainderDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getRemainderDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :OperationWebController.getRemainderDetails ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=operationController.getRemainderDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :OperationWebController.getRemainderDetails ");
		return returnObj;
	}
	
	@RequestMapping(value="/addRemainderDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addRemainderDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :OperationWebController.addRemainderDetails ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=operationController.addRemainderDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :OperationWebController.addRemainderDetails ");
		return returnObj;
	}
	
	@RequestMapping(value="/updateRemainderDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateRemainderDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :OperationWebController.updateRemainderDetails ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=operationController.updateRemainderDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :OperationWebController.updateRemainderDetails ");
		return returnObj;
	}
}
