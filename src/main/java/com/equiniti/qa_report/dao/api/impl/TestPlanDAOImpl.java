package com.equiniti.qa_report.dao.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.TestPlanDAO;
import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class TestPlanDAOImpl implements TestPlanDAO{

	private static final Logger LOG=Logger.getLogger(TestPlanDAOImpl.class);

	private AbstractHibernateDAOAPI<BtpEntity> abstractHibernateDAOAPI;

	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<BtpEntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	public List<BtpEntity> getBtpEntityList(Map<String, Object> restrictionMap) throws DaoException{
		LOG.debug("INSIDE getBtpEntityList(Map<String, Object> restrictionMap) Method");
		return abstractHibernateDAOAPI.getEntityList(BtpEntity.class, restrictionMap);
	}
	
	public int addBtpEntity(BtpEntity entity) throws DaoException{
		LOG.debug("INSIDE addBtpEntity(BtpEntity entity) Method");
		return abstractHibernateDAOAPI.saveEntity(entity);
	}
	
	public void updateBtpEntity(BtpEntity entity) throws DaoException{
		LOG.debug("INSIDE updateBtpEntity(BtpEntity entity) Method");
		abstractHibernateDAOAPI.updateEntity(entity);
	}
	
	public BtpEntity getBtpEntity(Map<String, Object> restrictionMap) throws DaoException{
		LOG.debug("INSIDE getBtpEntity(Map<String, Object> restrictionMap) Method");
		return abstractHibernateDAOAPI.getEntity(BtpEntity.class, restrictionMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUniqueBtpYearList() throws DaoException {
		List<String> returnList=null;
		try{
			returnList=(List<String>) abstractHibernateDAOAPI.processQuery("QUERY_6", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

}
