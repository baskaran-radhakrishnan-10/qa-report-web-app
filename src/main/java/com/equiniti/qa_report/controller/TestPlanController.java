package com.equiniti.qa_report.controller;

import java.util.HashMap;
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
import com.equiniti.qa_report.util.ApplicationConstants;

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
	
	public Map<String,Object> editTestPlanEntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=testPlanService.updateTestPlanEntry(paramMap);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, (Boolean) returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> addTestPlanEntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=testPlanService.addTestPlanEntry(paramMap);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, (Integer)returnObj);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}

}
