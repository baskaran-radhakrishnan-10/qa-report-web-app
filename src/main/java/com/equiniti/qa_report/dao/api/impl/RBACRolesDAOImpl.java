package com.equiniti.qa_report.dao.api.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.RBACRolesDAO;
import com.equiniti.qa_report.entity.Roles;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class RBACRolesDAOImpl implements RBACRolesDAO{
	private static final Logger LOG=Logger.getLogger(RBACDAOImpl.class);
	private AbstractHibernateDAOAPI<Roles> abstractHibernateDAOAPI;
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<Roles> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRolesNameList() throws DaoException {
		LOG.debug("Begin: RBACRolesDAOImpl.getRolesNameList");
		List<String> returnList=null;
		try{
			returnList=(List<String>) abstractHibernateDAOAPI.processQuery("QUERY_5", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACRolesDAOImpl.getRolesNameList");
		return returnList;
	}
	
	@Override
	public List<Roles> getRolesList() throws DaoException {
		LOG.debug("Begin: RBACRolesDAOImpl.getRolesList");
		List<Roles> returnList=null;
		try{
			returnList=(List<Roles>) abstractHibernateDAOAPI.getEntityList(Roles.class, null);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: RBACRolesDAOImpl.getRolesList");
		return returnList;
	}

}
