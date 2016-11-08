package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.LeaveDetailsService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("leaveDetailsController")
public class LeaveDetailsController {
	
	@Autowired
	private HttpSession session;
	
	//@Autowired
	private LeaveDetailsService leaveDetailsService;
	
	private CacheInstance CACHE_INS = null;
	
	public Map<String,Object> getLeaveDetailsEntries() throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=leaveDetailsService.getLeaveDetailsEntries();
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.ERROR);
			if(null != returnObj){
				CACHE_INS = CacheInstance.getInstance();
				returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, ((List<?>)returnObj).subList(0, 999));
				returnObjMap.put(ApplicationConstants.SIZE, CACHE_INS.getItemFromCache("ApplicationConstants.LeaveDetails_RECORDS_COUNT",(String) session.getAttribute(ApplicationConstants.USER_ID)));
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> getLeaveDetailsEntries(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=leaveDetailsService.getLeaveDetailsEntries(paramMap);
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.ERROR);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> editLeaveDetailsEntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=leaveDetailsService.updateLeaveDetailsEntry(paramMap);
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
	
	public Map<String,Object> addLeaveDetailsEntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=leaveDetailsService.addLeaveDetailsEntry(paramMap);
			if(null != returnObj){
				CACHE_INS = CacheInstance.getInstance();
				returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, (Integer)returnObj);
				returnObjMap.put(ApplicationConstants.SIZE, CACHE_INS.getItemFromCache("ApplicationConstants.LeaveDetails_RECORDS_COUNT",(String) session.getAttribute(ApplicationConstants.USER_ID)));
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> getDataFromCache(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=leaveDetailsService.getLeaveDetailsFromCache((Integer)paramMap.get("PAGE_NO"));
			returnObjMap.put(ApplicationConstants.STATUS, returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> filterLeaveDetails(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=leaveDetailsService.filterLeaveDetailsEntries(paramMap);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}

}
