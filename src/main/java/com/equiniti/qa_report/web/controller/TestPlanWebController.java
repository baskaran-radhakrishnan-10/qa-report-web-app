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

@Controller
@RequestMapping(value="/build_test_plan")
public class TestPlanWebController {
	
	private static final Logger LOG= Logger.getLogger(TestPlanWebController.class); 
	
	@Autowired
	private TestPlanController testPlanController;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showBuildTestPlanPage(Model model){
		/*try {
			model.addAttribute("BTP_ENTRIES", testPlanController.listTestPlanEntries());
		} catch (ControllerException e) {
			e.printStackTrace();
		}*/
		return "test_plan_page";
	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getData(@RequestBody Map<String,Object> inputData){
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj.put("BTP_ENTRIES", testPlanController.getTestPlanEntries());
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addData(@RequestBody Map<String,Object> inputData){
		return null;
	}
	
	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editData(@RequestBody Map<String,Object> inputData){
		Map<String,Object> returnObj=new HashMap<>();
		
		return returnObj;
	}
	
	@RequestMapping(value = "/searchData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> searchData(@RequestBody Map<String,Object> inputData){
		return null;
	}

}
