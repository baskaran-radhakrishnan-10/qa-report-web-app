package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.event.dsr.AddDSREvent;
import com.equiniti.qa_report.event.dsr.GetDSREvent;
import com.equiniti.qa_report.event.dsr.UpdateDSREvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.DSRService;

@Service("dsrService")
public class DSRServiceImpl extends BaseAPIImpl implements DSRService{
	
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
		return event.getDSREntityList().subList(0, 999);
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

}
