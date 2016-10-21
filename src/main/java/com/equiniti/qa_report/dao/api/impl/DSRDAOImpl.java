package com.equiniti.qa_report.dao.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.DSRDAO;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class DSRDAOImpl implements DSRDAO{

	private static final Logger LOG=Logger.getLogger(DSRDAOImpl.class);

	private AbstractHibernateDAOAPI<DSREntity> abstractHibernateDAOAPI;

	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<DSREntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	public List<DSREntity> getDSREntityList(Map<String, Object> restrictionMap) throws DaoException{
		LOG.debug("INSIDE getDSREntityList(Map<String, Object> restrictionMap) Method");
		return abstractHibernateDAOAPI.getEntityList(DSREntity.class, restrictionMap);
	}
	
	public int addDSREntity(DSREntity entity) throws DaoException{
		LOG.debug("INSIDE addDSREntity(DSREntity entity) Method");
		return abstractHibernateDAOAPI.saveEntity(entity);
	}
	
	public void updateDSREntity(DSREntity entity) throws DaoException{
		LOG.debug("INSIDE updateDSREntity(DSREntity entity) Method");
		abstractHibernateDAOAPI.updateEntity(entity);
	}
	
	public DSREntity getDSREntity(Map<String, Object> restrictionMap) throws DaoException{
		LOG.debug("INSIDE getDSREntity(Map<String, Object> restrictionMap) Method");
		return abstractHibernateDAOAPI.getEntity(DSREntity.class, restrictionMap);
	}

}
