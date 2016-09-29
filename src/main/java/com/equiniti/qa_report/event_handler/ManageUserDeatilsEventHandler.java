package com.equiniti.qa_report.event_handler;

import java.util.List;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.ManageUsersDAO;
import com.equiniti.qa_report.event.user_details.GetUserDeatilsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class ManageUserDeatilsEventHandler implements IEventHandler<IEvent>  {
	
	private static final Logger LOG=Logger.getLogger(ManageUserDeatilsEventHandler.class); 
	
	private ManageUsersDAO manageUsersDAO;
	
	public void setManageUsersDAO(ManageUsersDAO manageUsersDAO) {
		this.manageUsersDAO = manageUsersDAO;
	}


	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof GetUserDeatilsEvent) {
			LOG.debug("Event :" + GetUserDeatilsEvent.class.getName());
			GetUserDeatilsEvent eventObj = (GetUserDeatilsEvent) event;
			getUserDetails(eventObj);
		}
		//else if(event instanceof ){ }
		LOG.debug("processEvent END");
		}
		

	public void getUserDetails(GetUserDeatilsEvent event) throws EventException {
	//List<String> userDetailsList=null;
	try {
		event.setUserDetailsList(manageUsersDAO.getUserDetails(event.getRestrictionMap()));
	} catch (DaoException e) {
		LOG.error("DaoException Occured", e);
		throw new EventException(e.getFaultCode(), e);
	} catch (Exception e) {
		LOG.error("Unknown Exception Occured", e);
		throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
	}
//return userDetailsList;
}
}
