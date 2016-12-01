package com.equiniti.qa_report.dao.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.equiniti.qa_report.dao.api.ItemDetailsDAO;
import com.equiniti.qa_report.entity.ItemEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class ItemDetailsDAOImpl implements ItemDetailsDAO{
	
	private AbstractHibernateDAOAPI<ItemEntity> abstractHibernateDAOAPI;
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<ItemEntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@Override
	public List<ItemEntity> getItemDetailsList(Map<String, Object> restrictionMap) throws DaoException {
		List<ItemEntity> returnList=null;
		try{
			if(null == restrictionMap){
				restrictionMap = new HashMap<>();
			}
			restrictionMap.put("is_deleted", false);
			returnList=abstractHibernateDAOAPI.getEntityList(ItemEntity.class, restrictionMap);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUniqueItemDescription() throws DaoException {
		List<String> returnList=null;
		try{
			returnList=(List<String>) abstractHibernateDAOAPI.processQuery("QUERY_3", null, null, QueryOperationType.SELECT, QueryType.SQL, null);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnList;
	}

	@Override
	public int addItemDetails(ItemEntity entity) throws DaoException {
		int createdRow=0;
		try{
			createdRow=abstractHibernateDAOAPI.saveEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return createdRow;
	}

	@Override
	public void updateItemDetails(ItemEntity entity) throws DaoException {
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	@Override
	public void deleteItemDetails(int btpNo,int itemNo,int gKey) throws DaoException{
		abstractHibernateDAOAPI.bulkSQLNativeOperation(buildItemDetailsDeleteQuery(btpNo,itemNo,gKey));
	}
	
	private List<String> buildItemDetailsDeleteQuery(int btpNo,int itemNo,int gKey){
		List<String> queryList = new ArrayList<>();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("UPDATE ResourceTable SET is_deleted = 1 WHERE btpno = ").append(btpNo).append(" AND itemno = ").append(itemNo);
		queryList.add(queryBuffer.toString());
		queryBuffer = new StringBuffer();
		queryBuffer.append("UPDATE ItemTable SET is_deleted = 1 WHERE gKey = ").append(gKey);
		queryList.add(queryBuffer.toString());
		return queryList;
	}

}
