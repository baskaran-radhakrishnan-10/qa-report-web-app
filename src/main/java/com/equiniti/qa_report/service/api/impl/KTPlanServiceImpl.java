package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.event.kt_plan.AddKTPlanDetailsEvent;
import com.equiniti.qa_report.event.kt_plan.GetKTPlanDetailsEvent;
import com.equiniti.qa_report.event.kt_plan.UpdateKTPlanDetailsEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.KTPlanService;

@Service("ktPlanService")
public class KTPlanServiceImpl extends BaseAPIImpl implements KTPlanService {
	
	private static final Logger LOG=Logger.getLogger(KTPlanServiceImpl.class);
	
	@Override
	public List<KTPlan> getKTPlanDetails() throws APIException{
		LOG.info("Begin : KTPlanServiceImpl.getKTPlanDetails");
		GetKTPlanDetailsEvent event=null;
		try{
			event=getEvent(GetKTPlanDetailsEvent.class);
			LOG.info("event--> "+event);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("Eng : KTPlanServiceImpl.getKTPlanDetails");
		return event.getKtPlansList();
	}
	
	@Override
	public int addKTDetails(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :KTPlanServiceImpl.addKTDetails ");
		AddKTPlanDetailsEvent event=null;
		try{
			event=getEvent(AddKTPlanDetailsEvent.class);
			event.setRequestParam(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :KTPlanServiceImpl.addKTDetails ");
		return event.getRowId();
	}
	
	@Override
	public boolean updateKTDetails(Map<String, Object> inputParam) throws APIException {
		LOG.info("Begin :KTPlanServiceImpl.updateKTDetails ");
		UpdateKTPlanDetailsEvent event=null;
		try{
			event=getEvent(UpdateKTPlanDetailsEvent.class);
			event.setRestrictionMap(inputParam);
			processEvent(event);
		}catch(EventException e){
			throw new APIException(e.getFaultCode(),e);
		}catch(Exception e){
			throw new APIException(CommonFaultCode.UNKNOWN_ERROR,e);
		}
		LOG.info("End :KTPlanServiceImpl.updateKTDetails ");
		return event.isUpdated();
	}
}
