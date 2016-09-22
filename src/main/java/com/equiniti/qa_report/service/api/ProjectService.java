package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface ProjectService {

	List<String> getUniqueProjectNameList() throws APIException;
	
	List<ProjectEntity> getProjectList(Map<String, Object> inputParam) throws APIException;

	int addProject(Map<String, Object> inputParam) throws APIException;

	void updateProject(Map<String, Object> inputParam) throws APIException;

}
