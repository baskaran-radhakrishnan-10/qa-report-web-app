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

import com.equiniti.qa_report.controller.ProjectController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;

@Controller
@RequestMapping(value="/project")
public class ProjectWebController {
	
	private static final Logger LOG= Logger.getLogger(ProjectWebController.class); 
	
	@RequestMapping(value = "/manage_projects" , method = RequestMethod.GET)
	public String showManageProjectsPage(Model model) throws UIException{
		return "manage_projects_page";
	}
	
	@Autowired
	private ProjectController projectController;

	@RequestMapping(value="/getUniqueProjectList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUniqueProjectList(Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<String, Object>();
		try {
			returnObj=projectController.getUniqueProjectList(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/getProjectList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProjectList(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :ProjectWebController.getProjectList ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=projectController.getProjectList(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :ProjectWebController.getProjectList ");
		return returnObj;
	}
	
	@RequestMapping(value="/addProject", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addProject(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin:ProjectWebController.addProject");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=projectController.addProject(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End:ProjectWebController.addProject");
		return returnObj;
	}
	
	@RequestMapping(value="/updateProject", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateProject(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin :ProjectWebController.updateProject ");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=projectController.updateProject(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End :ProjectWebController.updateProject ");
		return returnObj;
	}
	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=projectController.deleteData(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
}
