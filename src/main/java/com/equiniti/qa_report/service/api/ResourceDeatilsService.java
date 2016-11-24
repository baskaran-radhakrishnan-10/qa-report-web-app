package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface ResourceDeatilsService {

	public List<String> getUniqueResourceDetails(Map<String, Object> inputParam) throws APIException;

	public List<ResourceEntity> getResourceDetails(Map<String, Object> inputParam) throws APIException;

	public int addResourceDetails(Map<String, Object> inputParam) throws APIException;

	public boolean updateResourceDeatils(Map<String, Object> inputParam) throws APIException;

	public void deleteResourceDeatils(Map<String, Object> paramMap) throws APIException;

}
