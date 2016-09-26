package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.form.model.TestPlanModelAttribute;

public interface TestPlanService {
	
	public List<TestPlanModelAttribute> listTestPlanEntries() throws APIException;
	
	public TestPlanModelAttribute getModelAttribute(Map<String,Object> paramMap) throws APIException;
	
	public int addTestPlanEntry(Map<String, Object> paramMap) throws APIException;
	
	public boolean updateTestPlanEntry(Map<String, Object> paramMap) throws APIException;

	public List<BtpEntity> getTestPlanEntries() throws APIException;

}
