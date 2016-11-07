package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.event.remainder_details.AddRemainderDetailsEvent;
import com.equiniti.qa_report.event.remainder_details.GetRemainderDetailsEvent;
import com.equiniti.qa_report.event.remainder_details.UpdateRemainderDetailsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.OperationService;
@Service("operationService")
public class OperationServiceImpl extends BaseAPIImpl implements OperationService {
	
	private static final Logger LOG= Logger.getLogger(OperationServiceImpl.class); 

	@Override
	public List<Remainder> getRemainderDetails() throws APIException {
		LOG.info("Begin: OperationServiceImpl.getRemainderDetails");
		GetRemainderDetailsEvent event=null;
		try{
			event=getEvent(GetRemainderDetailsEvent.class);
			event.setEntity(populateEntityFromList());
			LOG.info("event--> "+event);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End: OperationServiceImpl.getRemainderDetails");
		return event.getRemainderDetailsList();
	}
	
	@Override
	public int addRemainderDetails(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :OperationServiceImpl.addRemainderDetails ");
		AddRemainderDetailsEvent event=null;
		try{
			event=getEvent(AddRemainderDetailsEvent.class);
			event.setRequestParam(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :OperationServiceImpl.addRemainderDetails ");
		return event.getRowId();
	}
	
	@Override
	public boolean updateRemainderDetails(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :OperationServiceImpl.updateRemainderDetails ");
		UpdateRemainderDetailsEvent event=null;
		try{
			event=getEvent(UpdateRemainderDetailsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :OperationServiceImpl.updateRemainderDetails ");
		return event.isUpdated();
	}
	

	private Remainder populateEntityFromList(){

		Remainder entity=new Remainder();

		return entity;

	}

}
