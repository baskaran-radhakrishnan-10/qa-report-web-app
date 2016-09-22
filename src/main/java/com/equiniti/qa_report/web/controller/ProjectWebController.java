package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.ProjectController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;

@Controller
@RequestMapping(value="/project")
public class ProjectWebController {
	
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
	
}
