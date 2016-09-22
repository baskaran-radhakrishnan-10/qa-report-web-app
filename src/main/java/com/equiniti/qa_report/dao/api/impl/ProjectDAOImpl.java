package com.equiniti.qa_report.dao.api.impl;

import java.util.List;

import com.equiniti.qa_report.dao.api.ProjectDAO;
import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class ProjectDAOImpl implements ProjectDAO{
	
	private AbstractHibernateDAOAPI<ProjectEntity> abstractHibernateDAOAPI;
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<ProjectEntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUniqueProjectNames() throws DaoException {
		List<String> returnList=null;
		try{
			returnList=(List<String>) abstractHibernateDAOAPI.processQuery("QUERY_2", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

}
