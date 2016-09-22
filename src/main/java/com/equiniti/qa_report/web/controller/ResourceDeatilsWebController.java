package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.ResourceDeatilsController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;

@Controller
@RequestMapping(value="/resource_details")
public class ResourceDeatilsWebController {
	
	@Autowired
	private ResourceDeatilsController resourceDeatilsController;
	
	@RequestMapping(value="/getUniqueResources", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUniqueResourceDetails(Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=resourceDeatilsController.getUniqueResourceDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/getResourceDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getResourceDetailsByItem(Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		return returnObj;
	}
	
	@RequestMapping(value="/addResourceDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addResourceDetails(Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		return returnObj;
	}
	
	@RequestMapping(value="/updateResourceDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateResourceDeatils(Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		return returnObj;
	}
	
}
