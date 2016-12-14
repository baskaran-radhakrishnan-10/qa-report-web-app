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
import com.equiniti.qa_report.exception.api.exception.UIException;

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
			returnObj=ktPlanController.updateKTDetails(inputParam);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
		LOG.info("End: KTPlanWebController.updateKTDetails");
		return returnObj;
	}
	
	@RequestMapping(value = "/deleteData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteData(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=ktPlanController.deleteData(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}

}
