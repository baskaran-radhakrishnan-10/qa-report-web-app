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

import com.equiniti.qa_report.controller.LeaveDetailsController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;

@Controller
@RequestMapping(value="/leave")
public class LeaveDetailsWebController {
	
	private static final Logger LOG = Logger.getLogger(LeaveDetailsWebController.class);
	
	@Autowired
	private LeaveDetailsController leaveDetailsController;
	
	@RequestMapping(value = "/show")
	public String showLeaveDetailsPage(Model model){
		return "dsr_page";
	}
	
	@RequestMapping(value = "/getData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getData(@RequestBody Map<String,Object> inputData) throws UIException{
		LOG.debug("START getData() Method!!!");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj = leaveDetailsController.getLeaveDetailsEntries();
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		LOG.debug("END getData() Method!!!");
		return returnObj;
	}
	
	@RequestMapping(value = "/addData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addData(@RequestBody Map<String,Object> inputData) throws UIException{
		LOG.debug("START addData() Method!!!");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=leaveDetailsController.addLeaveDetailsEntry(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		LOG.debug("END addData() Method!!!");
		return returnObj;
	}
	
	@RequestMapping(value = "/getDataFromCache", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getDataFromCache(@RequestBody Map<String,Object> inputData) throws UIException{
		LOG.debug("START getDataFromCache() Method!!!");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=leaveDetailsController.getDataFromCache(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		LOG.debug("END addData() Method!!!");
		return returnObj;
	}
	
	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editData(@RequestBody Map<String,Object> inputData) throws UIException{
		LOG.debug("START editData() Method!!!");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=leaveDetailsController.editLeaveDetailsEntry(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		LOG.debug("END editData() Method!!!");
		return returnObj;
	}
	
	@RequestMapping(value = "/filterData", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> filterData(@RequestBody Map<String,Object> inputData) throws UIException{
		LOG.debug("START filterData() Method!!!");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj = leaveDetailsController.filterLeaveDetails(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		LOG.debug("END filterData() Method!!!");
		return returnObj;
	}
	
}
