package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface RBACDAO {
	
	public List<User> getUserDetails(Map<String,Object> restrictionMap) throws DaoException;
	
	public int addUserDetails(User entity) throws DaoException;
	
	public List<String> getUniqueUserList() throws DaoException;
	
	public void resetPassword(User entity) throws DaoException;

}