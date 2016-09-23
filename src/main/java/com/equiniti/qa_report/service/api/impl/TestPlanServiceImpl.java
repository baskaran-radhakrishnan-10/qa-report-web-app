package com.equiniti.qa_report.service.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.event.test_plan.GetTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.UpdateTestPlanEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.form.model.TestPlanModelAttribute;
import com.equiniti.qa_report.objectmapper.ObjectTranslatorAPI;
import com.equiniti.qa_report.service.api.TestPlanService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Service("testPlanService")
public class TestPlanServiceImpl extends BaseAPIImpl implements TestPlanService{

	private static final Logger LOG=Logger.getLogger(TestPlanServiceImpl.class);

	private ObjectTranslatorAPI objectTranslatorAPI;

	public void setObjectTranslatorAPI(ObjectTranslatorAPI objectTranslatorAPI) {
		this.objectTranslatorAPI = objectTranslatorAPI;
	}

	@Override
	public List<TestPlanModelAttribute> listTestPlanEntries() throws APIException {
		List<TestPlanModelAttribute> returnList=null;
		GetTestPlanEvent event = getEvent(GetTestPlanEvent.class);
		try {
			event.setListAll(true);
			processEvent(event);
			List<BtpEntity> entityList=event.getBtpEntityList();
			returnList=objectTranslatorAPI.listConverter(entityList.subList(0, 10), TestPlanModelAttribute.class, ApplicationConstants.OBJECT_MAPPER_TESTPLAN);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

	@Override
	public List<BtpEntity> getTestPlanEntries() throws APIException {
		GetTestPlanEvent event = getEvent(GetTestPlanEvent.class);
		try {
			event.setListAll(true);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return event.getBtpEntityList();
	}

	@Override
	public TestPlanModelAttribute getModelAttribute(Map<String, Object> paramMap) throws APIException {
		return null;
	}

	@Override
	public int addTestPlanEntry(TestPlanModelAttribute model) throws APIException {
		return 0;
	}

	@Override
	public boolean updateTestPlanEntry(Map<String, Object> paramMap) throws APIException {
		UpdateTestPlanEvent event=getEvent(UpdateTestPlanEvent.class);
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
}
