package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface ManageUsersDAO {
	
	public List<User> getUserDetails(Map<String,Object> restrictionMap) throws DaoException;
	

}
