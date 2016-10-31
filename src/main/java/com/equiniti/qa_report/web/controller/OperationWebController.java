package com.equiniti.qa_report.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.equiniti.qa_report.exception.api.exception.UIException;
import com.equiniti.qa_report.util.ApplicationConstants;

@Controller
@RequestMapping(value="operation")
public class OperationWebController {

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
	
	@RequestMapping(value = "/remainder_settings" , method = RequestMethod.GET)
	public String showRemainderSettingsPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Operations/Remainder Settings");
		return "under_construction_page";
	}

}
