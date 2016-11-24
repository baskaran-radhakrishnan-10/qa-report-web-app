package com.equiniti.qa_report.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ItemDetailsService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("itemDetailsController")
public class ItemDetailsController {
	
	@Autowired
	private ItemDetailsService itemDetailsService;
	
	public Map<String,Object> getItemDeatilsByBtoNo(Map<String,Object> inputParam) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=itemDetailsService.getItemDetailsByItem(inputParam);
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
	
	public Map<String,Object> getUniqueItemDescList(Map<String,Object> inputParam) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=itemDetailsService.getUniqueItemDescList();
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
	
	public Map<String,Object> addItemDetails(Map<String,Object> inputParam) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=itemDetailsService.addItemDetails(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Integer)returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
			returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> updateItemDeatils(Map<String,Object> inputParam) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=itemDetailsService.updateItemDeatils(inputParam);
			returnObjMap.put(ApplicationConstants.STATUS, (Boolean)returnObj ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public Map<String,Object> deleteItemDeatils(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			itemDetailsService.deleteItemDeatils(paramMap);
			returnObjMap.put(ApplicationConstants.STATUS, ApplicationConstants.SUCCESS);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}

}
