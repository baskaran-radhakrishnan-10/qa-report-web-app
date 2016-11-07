package com.equiniti.qa_report.dao.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.OperationDAO;
import com.equiniti.qa_report.entity.Remainder;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class OperationDAOImpl implements OperationDAO{
	
	private static final Logger LOG=Logger.getLogger(OperationDAOImpl.class);
	
	private AbstractHibernateDAOAPI<Remainder> abstractHibernateDAOAPI;
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<Remainder> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}
	
	@SuppressWarnings("unchecked")
	
	@Override
	public List<Remainder> getRemainderDetails(Map<String,Object> restrictionMap) throws DaoException {
		LOG.debug("Begin: OperationDAOImpl.getRemainderDetails");
		List<Remainder> returnList=null;
		try{
			returnList=abstractHibernateDAOAPI.getEntityList(Remainder.class, restrictionMap);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: OperationDAOImpl.getRemainderDetails");
		return returnList;
	}

	@Override
	public int addRemainderDetails(Remainder entity) throws DaoException {
		LOG.debug("Begin: OperationDAOImpl.addRemainderDetails");
		int createdRow=0;
		try{
			createdRow=abstractHibernateDAOAPI.saveEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: OperationDAOImpl.addRemainderDetails");
		return createdRow;
	}
	
	@Override
	public void updateRemainderDetails(Remainder entity) throws DaoException {
		LOG.debug("Begin: OperationDAOImpl.updateRemainderDetails");
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: OperationDAOImpl.updateRemainderDetails");
	}

}
