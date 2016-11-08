package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.entity.LeaveDetails;
import com.equiniti.qa_report.event.leave_details.AddLeaveDetailsEvent;
import com.equiniti.qa_report.event.leave_details.GetLeaveDetailsEvent;
import com.equiniti.qa_report.event.leave_details.UpdateLeaveDetailsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.LeaveDetailsService;
import com.equiniti.qa_report.util.ApplicationConstants;

public class LeaveDetailsServiceImpl extends BaseAPIImpl implements LeaveDetailsService{
	
	@Autowired
	private HttpSession session;
	
	@Override
	public int addLeaveDetailsEntry(Map<String, Object> paramMap) throws APIException {
		AddLeaveDetailsEvent event=getEvent(AddLeaveDetailsEvent.class);
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
	public boolean updateLeaveDetailsEntry(Map<String, Object> paramMap) throws APIException {
		UpdateLeaveDetailsEvent event=getEvent(UpdateLeaveDetailsEvent.class);
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
	public List<LeaveDetails> getLeaveDetailsEntries() throws APIException {
		GetLeaveDetailsEvent event = getEvent(GetLeaveDetailsEvent.class);
		try {
			event.setListAll(true);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getLeaveDetailsEntityList();
	}

	@Override
	public List<LeaveDetails> getLeaveDetailsEntries(Map<String, Object> restrictionMap) throws APIException {
		GetLeaveDetailsEvent event = getEvent(GetLeaveDetailsEvent.class);
		try {
			event.setRestrictionMap(restrictionMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getLeaveDetailsEntityList();
	}
	
	@Override
	public List<LeaveDetails> filterLeaveDetailsEntries(Map<String, Object> restrictionMap) throws APIException {
		GetLeaveDetailsEvent event = getEvent(GetLeaveDetailsEvent.class);
		try {
			event.setFilter(true);
			event.setRestrictionMap(restrictionMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getLeaveDetailsEntityList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Map<Integer,LeaveDetails> getLeaveDetailsFromCache(int pageNo) throws APIException {
		try {
			CacheInstance CACHE_INS=CacheInstance.getInstance();
			Map<Integer,Map<Integer,LeaveDetails>> pagedDsrEntity=(Map<Integer, Map<Integer, LeaveDetails>>) CACHE_INS.getItemFromCache("ApplicationConstants.",(String) session.getAttribute(ApplicationConstants.USER_ID));
			return pagedDsrEntity.get(pageNo);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
}
