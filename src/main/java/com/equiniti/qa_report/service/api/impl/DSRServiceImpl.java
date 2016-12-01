package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.event.dsr.AddDSREvent;
import com.equiniti.qa_report.event.dsr.DeleteDSREvent;
import com.equiniti.qa_report.event.dsr.GetDSREvent;
import com.equiniti.qa_report.event.dsr.UpdateDSREvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.DSRService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Service("dsrService")
public class DSRServiceImpl extends BaseAPIImpl implements DSRService{
	
	@Autowired
	private HttpSession session;
	
	@Override
	public int addDSREntry(Map<String, Object> paramMap) throws APIException {
		AddDSREvent event=getEvent(AddDSREvent.class);
		try{
			event.setParamMap(paramMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getAddedRow();
	}

	@Override
	public boolean updateDSREntry(Map<String, Object> paramMap) throws APIException {
		UpdateDSREvent event=getEvent(UpdateDSREvent.class);
		try{
			event.setParamMap(paramMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.isUpdated();
	}

	@Override
	public List<DSREntity> getDSREntries() throws APIException {
		GetDSREvent event = getEvent(GetDSREvent.class);
		try {
			event.setListAll(true);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getDSREntityList();
	}

	@Override
	public List<DSREntity> getDSREntries(Map<String, Object> restrictionMap) throws APIException {
		GetDSREvent event = getEvent(GetDSREvent.class);
		try {
			event.setRestrictionMap(restrictionMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getDSREntityList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteDSREntry(Map<String, Object> paramMap) throws APIException {
		DeleteDSREvent event=getEvent(DeleteDSREvent.class);
		try{
			if(paramMap.containsKey("dsrNo")){
				event.setDeleteEntityKey(Integer.parseInt(paramMap.get("dsrNo").toString()));
			}else if(paramMap.containsKey("dsrNoList")){
				event.setDeleteKeyList((List<Integer>) paramMap.get("dsrNoList"));
			}
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	@Override
	public List<DSREntity> filterDSREntries(Map<String, Object> restrictionMap) throws APIException {
		GetDSREvent event = getEvent(GetDSREvent.class);
		try {
			event.setFilter(true);
			event.setRestrictionMap(restrictionMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getDSREntityList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<Integer,DSREntity> getDSRFromCache(int pageNo) throws APIException {
		try {
			CacheInstance CACHE_INS=CacheInstance.getInstance();
			Map<Integer,Map<Integer,DSREntity>> pagedDsrEntity=(Map<Integer, Map<Integer, DSREntity>>) CACHE_INS.getItemFromCache(ApplicationConstants.PAGED_DSR_CACHE_ITEM,(String) session.getAttribute(ApplicationConstants.USER_ID));
			return pagedDsrEntity.get(pageNo);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
}
