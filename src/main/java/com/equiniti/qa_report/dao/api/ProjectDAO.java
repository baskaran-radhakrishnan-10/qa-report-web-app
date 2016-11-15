package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface ProjectDAO {

	public List<String> getUniqueProjectNames() throws DaoException;
	
	public List<ProjectEntity> getProjectList(Map<String, Object> restrictionMap) throws DaoException;
	
	public int addProject(ProjectEntity entity) throws DaoException;
	
	public void updateProject(ProjectEntity entity) throws DaoException;

}
