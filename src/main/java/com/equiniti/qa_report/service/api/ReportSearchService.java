package com.equiniti.qa_report.service.api;

import java.util.Map;

import com.equiniti.qa_report.exception.api.exception.APIException;

public interface ReportSearchService {
	
	public void buildBTPSummaryReport(Map<String,Object> paramMap) throws APIException;
	
	public void buildBTPMonthlyReport(Map<String,Object> paramMap) throws APIException;

	public void buildSelectedBTPReport(Map<String, Object> paramMap) throws APIException;

}
