package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.KTPlanService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("ktPlanController")
public class KTPlanController {
	
	private static final Logger LOG= Logger.getLogger(RBACController.class);
	@Autowired
	private KTPlanService ktPlanService;
	
	public Map<String,Object> getKTPlanDetails() throws ControllerException{
		LOG.info("Begin: KTPlanController.getKTPlanDetails");
		List<KTPlan> returnObj= null;
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			 returnObj=ktPlanService.getKTPlanDetails();
			 if(null != returnObj){
					returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
					returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
				}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End: KTPlanController.getKTPlanDetails");
		return returnObjMap;
	}
	
	public Map<String,Object> addKTDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :KTPlanController.addKTDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=ktPlanService.addKTDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End :KTPlanController.addKTDetails ");
		return returnObjMap;
	}
	
	public Map<String,Object> updateKTDetails(Map<String,Object> inputParam) throws ControllerException{
		LOG.info("Begin :KTPlanController.updateKTDetails ");
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=ktPlanService.updateKTDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean)returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("Begin :KTPlanController.updateKTDetails ");
		return returnObjMap;
	}
	
	public Map<String,Object> deleteData(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			ktPlanService.deleteData(paramMap);
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}

}
