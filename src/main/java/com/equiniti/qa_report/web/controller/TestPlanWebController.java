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

import com.equiniti.qa_report.controller.TestPlanController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;

@Controller
@RequestMapping(value="/build_test_plan")
public class TestPlanWebController {
	
	private static final Logger LOG= Logger.getLogger(TestPlanWebController.class); 
	
	@Autowired
	private TestPlanController testPlanController;
	
	@RequestMapping(value = "/show")
	public String showBuildTestPlanPage(Model model){
		return "test_plan_page";
	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj.put("BTP_ENTRIES", testPlanController.getTestPlanEntries());
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=testPlanController.addTestPlanEntry(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=testPlanController.editTestPlanEntry(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=testPlanController.deleteTestPlanEntry(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/filterData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> filterData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj = testPlanController.getTestPlanEntries(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/getUniqueBtpYearList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUniqueBtpYear(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj = testPlanController.getUniqueBtpYear(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
}
