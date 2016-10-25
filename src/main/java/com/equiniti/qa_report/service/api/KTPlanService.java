package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface KTPlanService {

	public List<KTPlan> getKTPlanDetails() throws APIException;
	
	public int addKTDetails(Map<String, Object> inputParam) throws APIException;
	
	public boolean updateKTDetails(Map<String, Object> inputParam) throws APIException;

}
