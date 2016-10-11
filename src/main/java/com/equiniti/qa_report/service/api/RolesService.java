package com.equiniti.qa_report.service.api;

import java.util.List;

import com.equiniti.qa_report.exception.api.exception.APIException;

public interface RolesService {

	List<String> getRolesList() throws APIException;


}
