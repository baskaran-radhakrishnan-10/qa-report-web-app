package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface DSRService {
	
	public int addDSREntry(Map<String, Object> paramMap) throws APIException;
	
	public boolean updateDSREntry(Map<String, Object> paramMap) throws APIException;

	public List<DSREntity> getDSREntries() throws APIException;
	
	public List<DSREntity> getDSREntries(Map<String,Object> restrictionMap) throws APIException;
	
	public Map<Integer,DSREntity> getDSRFromCache(int pageNo) throws APIException;

}
