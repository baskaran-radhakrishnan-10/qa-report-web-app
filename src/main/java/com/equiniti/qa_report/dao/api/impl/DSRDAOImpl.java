package com.equiniti.qa_report.dao.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.DSRDAO;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class DSRDAOImpl implements DSRDAO{

	private static final Logger LOG=Logger.getLogger(DSRDAOImpl.class);

	private AbstractHibernateDAOAPI<DSREntity> abstractHibernateDAOAPI;

	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<DSREntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	public List<DSREntity> getDSREntityList(Map<String, Object> restrictionMap) throws DaoException{
		LOG.debug("INSIDE getDSREntityList(Map<String, Object> restrictionMap) Method");
		if(null == restrictionMap){
			restrictionMap = new HashMap<>();
		}
		restrictionMap.put("is_deleted", false);
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
	
	@Override
	public void deleteDSREntity(int dsrNo) throws DaoException{
		LOG.debug("INSIDE deleteDSREntity(int dsrNo) Method");
		abstractHibernateDAOAPI.bulkSQLNativeOperation(buildDSRDeleteQuery(dsrNo));
	}
	
	@Override
	public void deleteDSREntityList(List<Integer> dsrNoList) throws DaoException{
		LOG.debug("INSIDE deleteDSREntityList(List<Integer> dsrNoList) Method");
		abstractHibernateDAOAPI.bulkSQLNativeOperation(buildDSRDeleteQuery(dsrNoList));
	}
	
	//btp.startdate between START_DATE and END_DATE
	
	@SuppressWarnings("unchecked")
	public List<DSREntity> filterDSREntityList(Map<String, Object> restrictionMap) throws DaoException{
		StringBuffer queryBuffer=new StringBuffer();
		Set<String> keySet = restrictionMap.keySet();
		List<String> keyList=new ArrayList<>(keySet);
		queryBuffer.append("FROM DSREntity WHERE ");
		for(int index = 0;index<keyList.size();index++){
			String key = keyList.get(index);
			if(key.indexOf("startDate") != -1){
				queryBuffer.append("dsrDate").append(" >= '").append(restrictionMap.get(key)).append("'");
			}else if(key.indexOf("endDate") != -1){
				queryBuffer.append("dsrDate").append(" <= '").append(restrictionMap.get(key)).append("'");
			}else if(key.indexOf("plannedDate") != -1){
				queryBuffer.append("dsrDate").append(" between ").append("'").append(restrictionMap.get(key)).append(" 00:00:00").append("'").append(" and ").append("'").append(restrictionMap.get(key)).append(" 23:59:59").append("'");
			}else if(key.indexOf("accomplishedDate") != -1){
				queryBuffer.append("dsrDate").append(" between ").append("'").append(restrictionMap.get(key)).append(" 00:00:00").append("'").append(" and ").append("'").append(restrictionMap.get(key)).append(" 23:59:59").append("'");
			}else{
				queryBuffer.append(key).append(" = '").append(restrictionMap.get(key)).append("'");
			}
			if(index < (keyList.size()-1)){
				queryBuffer.append(" AND ");
			}
		}
		return (List<DSREntity>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.HQL, queryBuffer.toString());
	}
	
	private List<String> buildDSRDeleteQuery(int dsrNo){
		List<String> queryList = new ArrayList<>();
		StringBuffer queryBuffer = new StringBuffer();
		queryBuffer.append("UPDATE DSRTable SET is_deleted = 1 WHERE sno = ").append(dsrNo);
		queryList.add(queryBuffer.toString());
		return queryList;
	}
	
	private List<String> buildDSRDeleteQuery(List<Integer> dsrNoList){
		List<String> queryList = new ArrayList<>();
		if(null != dsrNoList && !dsrNoList.isEmpty()){
			for(int btpNo : dsrNoList){
				queryList.addAll(buildDSRDeleteQuery(btpNo));
			}
		}
		return queryList;
	}

}
