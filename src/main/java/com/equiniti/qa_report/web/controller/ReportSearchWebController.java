package com.equiniti.qa_report.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.TestPlanController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;

@Controller(value="report_search")
public class ReportSearchWebController {
	
	@Autowired
	private TestPlanController testPlanController;

	
	@RequestMapping(value = "/btp_serach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> btpSearch(@RequestBody Map<String,Object> inputData) throws UIException{
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj=testPlanController.editTestPlanEntry(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		return returnObj;
	}
	
}
