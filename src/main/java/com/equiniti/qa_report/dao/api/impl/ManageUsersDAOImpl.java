package com.equiniti.qa_report.dao.api.impl;

import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.dao.api.ManageUsersDAO;
import com.equiniti.qa_report.entity.User;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class ManageUsersDAOImpl implements ManageUsersDAO {

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
}
