package com.equiniti.qa_report.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.form.model.TestPlanModelAttribute;
import com.equiniti.qa_report.service.api.TestPlanService;

@Component("testPlanController")
public class TestPlanController {
	
	@Autowired
	private TestPlanService testPlanService;
	
	public List<TestPlanModelAttribute> listTestPlanEntries() throws ControllerException{
		try {
			return testPlanService.listTestPlanEntries();
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		}catch(Exception e){
			 throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	public List<BtpEntity> getTestPlanEntries() throws ControllerException{
		try {
			return testPlanService.getTestPlanEntries();
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		}catch(Exception e){
			 throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	public Map<String,Object> editTestPlanEntry(Map<String,Object> input) throws ControllerException{
		try {
			 testPlanService.updateTestPlanEntry(input);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		}catch(Exception e){
			 throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return null;
	}

}
