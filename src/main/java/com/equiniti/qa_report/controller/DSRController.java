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
import com.equiniti.qa_report.service.api.DSRService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("dsrController")
public class DSRController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private DSRService dsrService;
	
	private CacheInstance CACHE_INS = null;
	
	public Map<String,Object> getDSREntries() throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=dsrService.getDSREntries();
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.ERROR);
			if(null != returnObj){
				CACHE_INS = CacheInstance.getInstance();
				returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, ((List<?>)returnObj).subList(0, 999));
				returnObjMap.put(ApplicationConstants.SIZE, CACHE_INS.getItemFromCache(ApplicationConstants.DSR_RECORDS_COUNT,(String) session.getAttribute(ApplicationConstants.USER_ID)));
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> getDSREntries(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=dsrService.getDSREntries(paramMap);
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
	
	public Map<String,Object> editDSREntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=dsrService.updateDSREntry(paramMap);
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
	
	public Map<String,Object> addDSREntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=dsrService.addDSREntry(paramMap);
			if(null != returnObj){
				CACHE_INS = CacheInstance.getInstance();
				returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, (Integer)returnObj);
				returnObjMap.put(ApplicationConstants.SIZE, CACHE_INS.getItemFromCache(ApplicationConstants.DSR_RECORDS_COUNT,(String) session.getAttribute(ApplicationConstants.USER_ID)));
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
			Object returnObj=dsrService.getDSRFromCache((Integer)paramMap.get("PAGE_NO"));
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
	
	public Map<String,Object> deleteDSREntry(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			dsrService.deleteDSREntry(paramMap);
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> filterBTP(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=dsrService.filterDSREntries(paramMap);
			returnObjMap.put(ApplicationConstants.STATUS,ApplicationConstants.SUCCESS);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
}
