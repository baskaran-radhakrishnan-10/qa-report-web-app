package com.equiniti.qa_report.dao.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.controller.OperationController;
import com.equiniti.qa_report.dao.api.ProjectDAO;
import com.equiniti.qa_report.entity.ProjectEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class ProjectDAOImpl implements ProjectDAO{
	private static final Logger LOG= Logger.getLogger(OperationController.class); 
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
	
	@Override
	public List<ProjectEntity> getProjectList(Map<String, Object> restrictionMap) throws DaoException {
		List<ProjectEntity> returnList=null;
		try{
			if(null == restrictionMap){
				restrictionMap = new HashMap<>();
			}
			restrictionMap.put("is_deleted", false);
			returnList=(List<ProjectEntity>) abstractHibernateDAOAPI.getEntityList(ProjectEntity.class, restrictionMap);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}
	
	@Override
	public int addProject(ProjectEntity entity) throws DaoException {
		LOG.info("Begin:ProjectDAOImpl.addProject");
		int createdRow=0;
		try{
			createdRow=abstractHibernateDAOAPI.saveEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End:ProjectDAOImpl.addProject");
		return createdRow;
	}
	
	@Override
	public void updateProject(ProjectEntity entity) throws DaoException {
		LOG.info("Begin:ProjectDAOImpl.updateProject");
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.info("End:ProjectDAOImpl.updateProject");
	}
	@Override
	public void deleteData(List<Integer> deleteRecordList) throws DaoException{
		abstractHibernateDAOAPI.bulkSQLNativeOperation(buildDeleteQuery(deleteRecordList));
	}
	
	private List<String> buildDeleteQuery(List<Integer> deleteRecordList){
		LOG.info("Begin: ProjectDAOImpl.buildDeleteQuery");
		StringBuffer queryBuffer = new StringBuffer();
		List<String> queryList = new ArrayList<>();
		if(null !=deleteRecordList && !deleteRecordList.isEmpty()){
			queryBuffer.append("UPDATE projectstable SET is_deleted = 1 WHERE projectid in (");
			for (Object i:deleteRecordList ){
				queryBuffer.append(i.toString()+",");
			}
			queryBuffer.deleteCharAt(queryBuffer.length() -1);
		}
		queryBuffer.append(")");
		queryList.add(queryBuffer.toString());
		LOG.info("End: ProjectDAOImpl.buildDeleteQuery");
		return queryList;
	}

}
