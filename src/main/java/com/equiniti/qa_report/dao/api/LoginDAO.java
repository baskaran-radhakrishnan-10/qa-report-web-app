package com.equiniti.qa_report.dao.api;

import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface LoginDAO {
	
	public User getUserByUserIdAndPassword(String userId,String password) throws DaoException;

}
