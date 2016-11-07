package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface OperationService {
	
	public List<Remainder> getRemainderDetails () throws APIException;
	
	public int addRemainderDetails(Map<String, Object> inputParam) throws APIException;
	
	public boolean updateRemainderDetails(Map<String, Object> inputParam) throws APIException;

}
