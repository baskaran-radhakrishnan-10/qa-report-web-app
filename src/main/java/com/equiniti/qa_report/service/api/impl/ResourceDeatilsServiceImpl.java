package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.event.resource_details.AddResourceDeatilsEvent;
import com.equiniti.qa_report.event.resource_details.DeleteResourceDetailsEvent;
import com.equiniti.qa_report.event.resource_details.GetResourceDeatilsEvent;
import com.equiniti.qa_report.event.resource_details.UpdateResourceDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.exception.api.faultcode.EventFaultCodes;
import com.equiniti.qa_report.service.api.ResourceDeatilsService;

@Service("resourceDeatilsService")
public class ResourceDeatilsServiceImpl extends BaseAPIImpl implements ResourceDeatilsService{

	@Override
	public List<String> getUniqueResourceDetails(Map<String, Object> inputParam) throws APIException {
		GetResourceDeatilsEvent event=null;
		try{
			event=getEvent(GetResourceDeatilsEvent.class);
			event.setUniqueListRequired(true);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getResourceNameList();
	}

	@Override
	public List<ResourceEntity> getResourceDetails(Map<String, Object> inputParam) throws APIException {
		GetResourceDeatilsEvent event=null;
		try{
			event=getEvent(GetResourceDeatilsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getResourceEntityList();
	}

	@Override
	public int addResourceDetails(Map<String, Object> inputParam) throws APIException {
		AddResourceDeatilsEvent event=null;
		try{
			event=getEvent(AddResourceDeatilsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getRowId();
	}

	@Override
	public boolean updateResourceDeatils(Map<String, Object> inputParam) throws APIException {
		UpdateResourceDeatilsEvent event=null;
		try{
			event=getEvent(UpdateResourceDeatilsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.isUpdated();
	}
	
	@Override
	public void deleteResourceDeatils(Map<String, Object> paramMap) throws APIException {
		DeleteResourceDetailsEvent event=getEvent(DeleteResourceDetailsEvent.class);
		try{
			if(paramMap.containsKey("gKey")){
				event.setgKey(Integer.parseInt(paramMap.get("gKey").toString()));
				processEvent(event);
			}else{
				throw new APIException(EventFaultCodes.UN_KNOWN_ERROR,null);
			}
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
	}
	
}
