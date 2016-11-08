package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.LeaveDetails;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface LeaveDetailsService {

	public int addLeaveDetailsEntry(Map<String, Object> paramMap) throws APIException;

	public boolean updateLeaveDetailsEntry(Map<String, Object> paramMap) throws APIException;

	public List<LeaveDetails> getLeaveDetailsEntries() throws APIException;

	public List<LeaveDetails> getLeaveDetailsEntries(Map<String, Object> restrictionMap) throws APIException;

	public List<LeaveDetails> filterLeaveDetailsEntries(Map<String, Object> restrictionMap) throws APIException;

	public Map<Integer, LeaveDetails> getLeaveDetailsFromCache(int pageNo) throws APIException;

}
