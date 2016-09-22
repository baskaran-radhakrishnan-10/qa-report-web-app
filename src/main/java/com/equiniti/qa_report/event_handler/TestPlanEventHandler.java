package com.equiniti.qa_report.event_handler;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.TestPlanDAO;
import com.equiniti.qa_report.event.test_plan.AddTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.GetTestPlanEvent;
import com.equiniti.qa_report.event.test_plan.UpdateTestPlanEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;

public class TestPlanEventHandler implements IEventHandler<IEvent> {

    private static final Logger LOG = Logger.getLogger(TestPlanEventHandler.class.getName());
    
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
        }else {
            LOG.debug("Unknow Event");
        }
        LOG.debug("processEvent END");
    }

    private void addTestPlan(AddTestPlanEvent event) throws EventException {
        try {
        	testPlanDAO.addBtpEntity(event.getBtpEntity());
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }

    private void updateTestPlan(UpdateTestPlanEvent event) throws EventException {
        try {
        	testPlanDAO.updateBtpEntity(event.getBtpEntity());
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }

    private void getTestPlan(GetTestPlanEvent event) throws EventException {
        try {
            if (event.isListAll()) {
                event.setBtpEntityList(testPlanDAO.getBtpEntityList(event.getRestrictionMap()));
            } else if (null != event.getRestrictionMap()) {
                event.setBtpEntity(testPlanDAO.getBtpEntity(event.getRestrictionMap()));
            } 
        } catch (DaoException e) {
            throw new EventException(e.getFaultCode(), e);
        } catch (Exception e) {
            throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
        }
    }
    
}
