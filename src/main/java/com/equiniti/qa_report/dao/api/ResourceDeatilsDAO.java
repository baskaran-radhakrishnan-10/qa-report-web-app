package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface ResourceDeatilsDAO {
	
	public List<String> getUniqueResourceNames() throws DaoException;
	
	public List<ResourceEntity> getResourceDetailsList(Map<String,Object> restrictionMap) throws DaoException;
	
	public int addResourceDetails(ResourceEntity entity) throws DaoException;
	
	public void updateResourceDetails(ResourceEntity entity) throws DaoException;

	public void deleteResourceDetails(int gKey) throws DaoException;

}
