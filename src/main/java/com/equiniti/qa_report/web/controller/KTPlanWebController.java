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

import com.equiniti.qa_report.controller.KTPlanController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;

@Controller
@RequestMapping(value="/kt_plan")
public class KTPlanWebController {
	
	private static final Logger LOG= Logger.getLogger(RBACWebController.class); 
	
	@Autowired
	private KTPlanController ktPlanController;
	
	@RequestMapping(value = "/ktPlan", method = RequestMethod.GET)
	public String manageUsersPage(Model model){
		
		return "kt_plan_page";
	}
	
	@RequestMapping(value="/getKTPlanDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getUserDetails(){
		LOG.info("Begin: KTPlanWebController.getUserDetails");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			returnObjMap=ktPlanController.getKTPlanDetails();			
			LOG.info("KTPlanWebController.getKTPlanDetails.returnObjMap--> "+ returnObjMap);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: KTPlanWebController.getKTPlanDetails");
		return returnObjMap;
	}
	@RequestMapping(value="/addKTDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addKTDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin: KTPlanWebController.addKTDetails");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			LOG.info("KTPlanWebController.addKTDetails.inputParam--> "+ inputParam);
			returnObj=ktPlanController.addKTDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: KTPlanWebController.addKTDetails");
		return returnObj;
	}
	
	@RequestMapping(value="/updateKTDetails", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateKTDetails(@RequestBody Map<String,Object> inputParam){
		LOG.info("Begin: KTPlanWebController.updateKTDetails");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			LOG.info("KTPlanWebController.updateKTDetails.inputParam--> "+ inputParam);
			returnObj=ktPlanController.updateKTDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: KTPlanWebController.updateKTDetails");
		return returnObj;
	}

}
