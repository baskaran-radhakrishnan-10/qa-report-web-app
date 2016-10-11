package com.equiniti.qa_report.dao.api;

import java.util.List;

import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface RBACRolesDAO {

	public List<String> getRolesNameList() throws DaoException;
	
	public List<Roles> getRolesList() throws DaoException;

}
