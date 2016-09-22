package com.equiniti.qa_report.dao.api;

import java.util.List;

import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface ProjectDAO {

	public List<String> getUniqueProjectNames() throws DaoException;

}
