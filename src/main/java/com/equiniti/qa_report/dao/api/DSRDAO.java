package com.equiniti.qa_report.dao.api;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;

public interface DSRDAO {
	
	public List<DSREntity> getDSREntityList(Map<String, Object> restrictionMap) throws DaoException;
	
	public int addDSREntity(DSREntity entity) throws DaoException;
	
	public void updateDSREntity(DSREntity entity) throws DaoException;
	
	public DSREntity getDSREntity(Map<String, Object> restrictionMap) throws DaoException;

}
