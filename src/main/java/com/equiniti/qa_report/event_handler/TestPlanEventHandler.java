package com.equiniti.qa_report.event_handler;

import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import com.equiniti.qa_report.dao.api.TestPlanDAO;
import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.event.test_plan.AddTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.DeleteTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.GetTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.UpdateTestPlanEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.util.CommonUtil;

public class TestPlanEventHandler implements IEventHandler<IEvent> {

    private static final Logger LOG = Logger.getLogger(TestPlanEventHandler.class.getName());
    
    private ObjectMapper objMapper=new ObjectMapper();
    
    private TestPlanDAO testPlanDAO;

	public void setTestPlanDAO(TestPlanDAO testPlanDAO) {
		this.testPlanDAO = testPlanDAO;
	}

	public void processEvent(IEvent event) throws EventException {
        LOG.debug("processEvent START");
        if (event instanceof AddTestPlanEvent) {
            LOG.debug("Event :" + AddTestPlanEvent.class.getName());
            AddTestPlanEvent eventObj = (AddTestPlanEvent) event;
            addTestPlan(eventObj);
        }else if (event instanceof UpdateTestPlanEvent) {
            LOG.debug("Event :" + UpdateTestPlanEvent.class.getName());
            UpdateTestPlanEvent eventObj = (UpdateTestPlanEvent) event;
            updateTestPlan(eventObj);
        }else if (event instanceof GetTestPlanEvent) {
            LOG.debug("Event :" + GetTestPlanEvent.class.getName());
            GetTestPlanEvent eventObj = (GetTestPlanEvent) event;
            getTestPlan(eventObj);
        }else if (event instanceof DeleteTestPlanEvent) {
            LOG.debug("Event :" + DeleteTestPlanEvent.class.getName());
            DeleteTestPlanEvent eventObj = (DeleteTestPlanEvent) event;
            deleteTestPlan(eventObj);
        }else {
            LOG.debug("Unknow Event");
        }
        LOG.debug("processEvent END");
    }

    private void addTestPlan(AddTestPlanEvent event) throws EventException {
        try {
        	event.setAddedRow(testPlanDAO.addBtpEntity(populateEntityFromMap(event.getParamMap())));
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }

    private void updateTestPlan(UpdateTestPlanEvent event) throws EventException {
        try {
        	testPlanDAO.updateBtpEntity(populateEntityFromMap(event.getParamMap()));
        } catch (DaoException e) {
        	event.setUpdated(false);
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
        	event.setUpdated(false);
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }

    private void getTestPlan(GetTestPlanEvent event) throws EventException {
        try {
        	if (event.isUniqueYearRequired()) {
                event.setBtpYearList(testPlanDAO.getUniqueBtpYearList());
            } else if (event.isListAll() || (null != event.getRestrictionMap())) {
                event.setBtpEntityList(testPlanDAO.getBtpEntityList(event.getRestrictionMap()));
            } else if (null != event.getRestrictionMap()) {
                event.setBtpEntityList(testPlanDAO.getBtpEntityList(event.getRestrictionMap()));
            } 
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }
    
    private void deleteTestPlan(DeleteTestPlanEvent event) throws EventException {
        try {
        	if(null != event.getDeleteKeyList()){
        		testPlanDAO.deleteBtpEntityByBTPNoList(event.getDeleteKeyList());
        	}else if(event.getDeleteEntityKey() > 0){
        		testPlanDAO.deleteBtpEntityByBTPNo(event.getDeleteEntityKey());
        	}
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }
    
    private BtpEntity populateEntityFromMap(Map<String,Object> mapObject){
    	BtpEntity entity=objMapper.convertValue(CommonUtil.removeTransientObject(mapObject), BtpEntity.class);
    	entity.setUpdatesDate(new DateTime(Calendar.getInstance().getTime().getTime()));
    	if(null == entity.getCreatedDate()){
    		entity.setCreatedDate(new DateTime(Calendar.getInstance().getTime().getTime()));
    	}
    	return entity;
    }
    
}
