package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface ReportSearchDAO {

	public List<Map<String, Object>> getBtpSummaryReportData(Map<String, Object> paramMap) throws DaoException;

	public List<Map<String, Object>> getSelectedBtpReportData(Map<String, Object> paramMap) throws DaoException;

	public List<Map<String, Object>> getUserReportData(Map<String, Object> paramMap) throws DaoException;

}
