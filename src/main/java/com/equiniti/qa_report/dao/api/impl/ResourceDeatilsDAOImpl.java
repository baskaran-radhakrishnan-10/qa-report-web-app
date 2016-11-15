package com.equiniti.qa_report.dao.api.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.dao.api.ResourceDeatilsDAO;
import com.equiniti.qa_report.entity.ResourceEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class ResourceDeatilsDAOImpl implements ResourceDeatilsDAO{

	private AbstractHibernateDAOAPI<ResourceEntity> abstractHibernateDAOAPI;

	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<ResourceEntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUniqueResourceNames() throws DaoException {
		List<String> returnList=null;
		try{
			returnList=(List<String>) abstractHibernateDAOAPI.processQuery("QUERY_1", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

	@Override
	public List<ResourceEntity> getResourceDetailsList(Map<String, Object> restrictionMap) throws DaoException {
		List<ResourceEntity> returnList=null;
		try{
			returnList=abstractHibernateDAOAPI.getEntityList(ResourceEntity.class, restrictionMap);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

	@Override
	public int addResourceDetails(ResourceEntity entity) throws DaoException {
		int createdRow=0;
		try{
			createdRow=abstractHibernateDAOAPI.saveEntity(entity);
			updateItemActTimeByQuery(entity.getItemNo(), entity.getBtpNo().getgKey());
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return createdRow;
	}

	@Override
	public void updateResourceDetails(ResourceEntity entity) throws DaoException {
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
			updateItemActTimeByQuery(entity.getItemNo(), entity.getBtpNo().getgKey());
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private void updateItemActTimeByQuery(int itemNo,int btpNo) throws DaoException {
		try{
			abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.INSERT_UPDATE_DELETE, QueryType.SQL, constructDynamicQuery(itemNo, btpNo));
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	private String constructDynamicQuery(int itemNo,int btpNo){
		String dynamicQuery = null;
		Map<String,String> queryMap=new LinkedHashMap<>();
		queryMap.put("QUERY_7", "QUERY_7");
		dynamicQuery=abstractHibernateDAOAPI.constructQuery(queryMap);
		dynamicQuery=dynamicQuery.replace("ITEM_NO", String.valueOf(itemNo));
		dynamicQuery=dynamicQuery.replace("BTP_NO", String.valueOf(btpNo));
		return dynamicQuery;
	}

}
