package com.equiniti.qa_report.service.api.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.equiniti.qa_report.event.btp_report.BuildBTPMonthlyReportEvent;
import com.equiniti.qa_report.event.btp_report.BuildBTPSummaryReportEvent;
import com.equiniti.qa_report.event.btp_report.SelectedBTPReportEvent;
import com.equiniti.qa_report.eventapi.eventhandling.generic.BaseAPIImpl;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ReportSearchService;

@Service("reportSearchService")
public class ReportSearchServiceImpl extends BaseAPIImpl implements ReportSearchService{

	@Override
	public void buildBTPSummaryReport(Map<String,Object> paramMap) throws APIException {
		BuildBTPSummaryReportEvent event = getEvent(BuildBTPSummaryReportEvent.class);
		try {
			event.setParamMap(paramMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}

	@Override
	public void buildSelectedBTPReport(Map<String,Object> paramMap) throws APIException {
		SelectedBTPReportEvent event = getEvent(SelectedBTPReportEvent.class);
		try {
			event.setParamMap(paramMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}

	@Override
	public void buildBTPMonthlyReport(Map<String,Object> paramMap) throws APIException {
		BuildBTPMonthlyReportEvent event = getEvent(BuildBTPMonthlyReportEvent.class);
		try {
			event.setParamMap(paramMap);
			processEvent(event);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch (Exception e) {
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
}
