package com.equiniti.qa_report.service.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.event.test_plan.AddTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.GetTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.UpdateTestPlanEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.form.model.TestPlanModelAttribute;
import com.equiniti.qa_report.objectmapper.ObjectTranslatorAPI;
import com.equiniti.qa_report.service.api.ReportSearchService;
import com.equiniti.qa_report.service.api.TestPlanService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Service("testPlanService")
public class TestPlanServiceImpl extends BaseAPIImpl implements TestPlanService{

	private static final Logger LOG=Logger.getLogger(TestPlanServiceImpl.class);
	
	@Autowired
	private HttpSession session;

	private ObjectTranslatorAPI objectTranslatorAPI;
	
	@Autowired
	private ReportSearchService reportSearchService;

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
	public List<BtpEntity> getTestPlanEntries(Map<String,Object> restrictionMap) throws APIException {
		GetTestPlanEvent event = getEvent(GetTestPlanEvent.class);
		try {
			event.setRestrictionMap(restrictionMap);
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
	public int addTestPlanEntry(Map<String, Object> paramMap) throws APIException {
		AddTestPlanEvent event=getEvent(AddTestPlanEvent.class);
		try{
			paramMap.put("createdBy", ((User)session.getAttribute(ApplicationConstants.USER_OBJ)).getUserId());
			paramMap.put("updatedBy", ((User)session.getAttribute(ApplicationConstants.USER_OBJ)).getUserId());
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
	
	public List<BtpEntity> filterBTP(Map<String,Object> paramMap) throws APIException {
		LOG.debug("START filterBTP(Map<String,Object> paramMap) METHOD");
		List<BtpEntity> entityList=getTestPlanEntries(paramMap);
		if(null != entityList && !entityList.isEmpty()){
			StringBuffer buffer=new StringBuffer();
			int lastIndex = (entityList.size() - 1) ;
			for(BtpEntity entity : entityList){
				buffer.append(entity.getgKey()).append(entityList.indexOf(entity) == lastIndex ? "" : ",");
			}
			paramMap.put("BTP_NO_LIST", buffer.toString());
			reportSearchService.buildBTPSummaryReport(paramMap);
		}
		LOG.debug("END filterBTP(Map<String,Object> paramMap) METHOD");
		return entityList;
	}
	
}
