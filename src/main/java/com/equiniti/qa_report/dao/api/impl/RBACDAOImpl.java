package com.equiniti.qa_report.dao.api.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.RBACDAO;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class RBACDAOImpl implements RBACDAO {
	
	private static final Logger LOG=Logger.getLogger(RBACDAOImpl.class);
	
	private AbstractHibernateDAOAPI<User> abstractHibernateDAOAPI;

	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<User> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@SuppressWarnings("unchecked")
	
	@Override
	public List<User> getUserDetails(Map<String,Object> restrictionMap) throws DaoException {
		List<User> returnList=null;
		try{
			//restrictionMap.put("is_active", true);
			returnList=abstractHibernateDAOAPI.getEntityList(User.class, restrictionMap);
			
			//returnList=(List<User>)abstractHibernateDAOAPI.processQuery("QUERY_4", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
			
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

	@Override
	public int addUserDetails(User entity) throws DaoException {
		LOG.debug("Begin: RBACDAOImpl.addUserDetails");
		int createdRow=0;
		try{
			LOG.debug("AddUserDAOImpl.addUserDetails.entity--> "+entity);
			createdRow=abstractHibernateDAOAPI.saveEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACDAOImpl.addUserDetails");
		return createdRow;
	}
	@Override
	public List<String> getUniqueUserList() throws DaoException {
		LOG.debug("Begin: RBACDAOImpl.getUniqueUserList");
		List<String> returnList=null;
		try{
			returnList=(List<String>) abstractHibernateDAOAPI.processQuery("QUERY_5", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
			LOG.debug(": RBACDAOImpl.getUniqueUserList.returnList--> : " + returnList);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACDAOImpl.getUniqueUserList");
		return returnList;
	}
	
	@Override
	public void resetPassword(User entity) throws DaoException {
		LOG.debug("Begin: RBACDAOImpl.resetPassword");
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACDAOImpl.resetPassword");
	}
	@Override
	public void updateUserDetails(User entity) throws DaoException {
		LOG.debug("Begin: KTPlanDAOImpl.updateUserDetails");
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: KTPlanDAOImpl.updateUserDetails");
	}
}
