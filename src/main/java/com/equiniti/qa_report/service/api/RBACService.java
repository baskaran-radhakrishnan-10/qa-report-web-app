package com.equiniti.qa_report.service.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.APIException;

public interface RBACService {
	
	public int addUserDetails(Map<String, Object> inputParam) throws APIException;
	
	public List<User> getUserDetails() throws APIException;
	
	public List<String> getUniqueUserList() throws APIException;
	
	public boolean resetPassword(Map<String, Object> inputParam) throws APIException;
	
	public List<Roles> getRolesList() throws APIException;
	
	public boolean updateUserDetails(Map<String, Object> inputParam) throws APIException;

	public Map<String, Object> getRoleResourcesInfo() throws APIException;

}
