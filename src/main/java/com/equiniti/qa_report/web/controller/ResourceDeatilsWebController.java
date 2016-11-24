package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.ResourceDeatilsController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;

@Controller
@RequestMapping(value="/resource_details")
public class ResourceDeatilsWebController {
	
	@Autowired
	private ResourceDeatilsController resourceDeatilsController;
	
	@RequestMapping(value="/getUniqueResources", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUniqueResourceDetails(@RequestBody Map<String,Object> inputParam){
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
	public Map<String,Object> getResourceDetailsByItem(@RequestBody Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=resourceDeatilsController.getResourceDetailsByItem(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/addResourceDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addResourceDetails(@RequestBody Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=resourceDeatilsController.addResourceDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value="/updateResourceDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateResourceDeatils(@RequestBody Map<String,Object> inputParam){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=resourceDeatilsController.updateResourceDeatils(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=resourceDeatilsController.deleteResourceDeatils(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
}
