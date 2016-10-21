package com.equiniti.qa_report.event_handler;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.dao.api.ReportSearchDAO;
import com.equiniti.qa_report.event.btp_report.BuildBTPMonthlyReportEvent;
import com.equiniti.qa_report.event.btp_report.BuildBTPSummaryReportEvent;
import com.equiniti.qa_report.event.btp_report.SelectedBTPReportEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.IEvent;
import com.equiniti.qa_report.eventapi.eventhandling.handler.IEventHandler;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.exception.EventException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.queue.connector.ReportQueueConnector;
import com.equiniti.qa_report.util.ApplicationConstants;

public class ReportSearchEventHandler implements IEventHandler<IEvent> {

	private static final Logger LOG  = Logger.getLogger(ReportSearchEventHandler.class);

	@Autowired
	private HttpSession session;
	
	@Autowired
	private ReportQueueConnector reportQueueConnector;
	
	private ReportSearchDAO reportSearchDAO;

	public void setReportSearchDAO(ReportSearchDAO reportSearchDAO) {
		this.reportSearchDAO = reportSearchDAO;
	}

	@Override
	public void processEvent(IEvent event) throws EventException {
		LOG.debug("processEvent START");
		if (event instanceof BuildBTPSummaryReportEvent) {
			LOG.debug("Event :" + BuildBTPSummaryReportEvent.class.getName());
			BuildBTPSummaryReportEvent eventObj = (BuildBTPSummaryReportEvent) event;
			buildBTPSummaryByFilter(eventObj);
		}else if (event instanceof SelectedBTPReportEvent) {
			LOG.debug("Event :" + SelectedBTPReportEvent.class.getName());
			SelectedBTPReportEvent eventObj = (SelectedBTPReportEvent) event;
			buildSelectedBTPReport(eventObj);
		}else if (event instanceof BuildBTPMonthlyReportEvent) {
			LOG.debug("Event :" + BuildBTPMonthlyReportEvent.class.getName());
			BuildBTPMonthlyReportEvent eventObj = (BuildBTPMonthlyReportEvent) event;
			buildBTPMonthlyReportByFilter(eventObj);
		}else {
			LOG.debug("Unknow Event");
		}
		LOG.debug("processEvent END");
	}

	public void buildBTPSummaryByFilter(BuildBTPSummaryReportEvent event) throws EventException{
		try {
			Map<String,Object> exportObject=new HashMap<>();
			exportObject.put("REPORT_DATA", reportSearchDAO.getBtpSummaryReportData(event.getParamMap()));
			exportObject.put("REPORT_TYPE", "BTP_SUMMARY_REPORT");
			exportObject.put("USER_ID", session.getAttribute(ApplicationConstants.USER_ID).toString());
			reportQueueConnector.produce(exportObject);
		}catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		}catch (Exception e) {
			if(e.getCause() instanceof JMSException){
				LOG.debug(e.getMessage());
			}else{
				throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
			}
		}
	}

	public void buildSelectedBTPReport(SelectedBTPReportEvent event) throws EventException{
		try {
			Map<String,Object> exportObject=new HashMap<>();
			exportObject.put("REPORT_DATA", reportSearchDAO.getSelectedBtpReportData(event.getParamMap()));
			exportObject.put("REPORT_TYPE", "SELECTED_BTP_REPORT");
			exportObject.put("USER_ID", session.getAttribute(ApplicationConstants.USER_ID).toString());
			reportQueueConnector.produce(exportObject);
		}catch (DaoException e) {
			throw new EventException(e.getFaultCode(), e);
		}catch (Exception e) {
			if(e.getCause() instanceof JMSException){
				LOG.debug(e.getMessage());
			}else{
				throw new EventException(CommonFaultCode.UNKNOWN_ERROR, e);
			}
		}
	}

	public void buildBTPMonthlyReportByFilter(BuildBTPMonthlyReportEvent event){

	}

}
