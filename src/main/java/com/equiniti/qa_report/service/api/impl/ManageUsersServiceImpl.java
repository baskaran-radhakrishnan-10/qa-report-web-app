package com.equiniti.qa_report.service.api.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.user_details.GetUserDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ManageUsersService;

@Service("manageUsersService")
public class ManageUsersServiceImpl extends BaseAPIImpl implements ManageUsersService {
	private static final Logger LOG= Logger.getLogger(ManageUsersServiceImpl.class); 
	@Override
	public List<User> getUserDetails() throws APIException {
		LOG.info("Begin: ManageUsersServiceImpl.getUserDetails");
		GetUserDeatilsEvent event=null;
		try{
			event=getEvent(GetUserDeatilsEvent.class);
			event.setEntity(populateUserDetailsEntityFromList());
			LOG.info("event--> "+event);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End: ManageUsersServiceImpl.getUserDetails");
		return event.getUserDetailsList();
	}
	
private User populateUserDetailsEntityFromList(){
		
	User entity=new User();
		
		return entity;
		
	}

}
