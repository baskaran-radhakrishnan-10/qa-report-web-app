package com.equiniti.qa_report.service.api.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.event.roles.GetRolesEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.RolesService;

@Service("rolesService")
public class RolesServiceImpl extends BaseAPIImpl implements RolesService{
	
	@Override
	public List<String> getRolesList() throws APIException {
		GetRolesEvent event=null;
		try{
			event=getEvent(GetRolesEvent.class);
			event.setUniqueListRequired(true);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		return event.getRolesList();
	}


}
