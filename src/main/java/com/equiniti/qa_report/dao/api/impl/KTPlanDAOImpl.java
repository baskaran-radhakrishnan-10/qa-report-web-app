package com.equiniti.qa_report.dao.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.equiniti.qa_report.dao.api.KTPlanDAO;
import com.equiniti.qa_report.entity.KTPlan;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

public class KTPlanDAOImpl implements KTPlanDAO {
	
	private static final Logger LOG=Logger.getLogger(RBACDAOImpl.class);
	
	private AbstractHibernateDAOAPI<KTPlan> abstractHibernateDAOAPI;
	
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<KTPlan> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@Override
	public List<KTPlan> getKTPlanDetails(Map<String,Object> restrictionMap) throws DaoException {
		LOG.debug("Begin: KTPlanDAOImpl.getKTPlanDetails");
		List<KTPlan> returnList=null;
		try{
			if(null == restrictionMap){
				restrictionMap = new HashMap<>();
			}
			restrictionMap.put("is_deleted", false);
			returnList=abstractHibernateDAOAPI.getEntityList(KTPlan.class, restrictionMap);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: KTPlanDAOImpl.getKTPlanDetails");
		return returnList;
	}
	
	@Override
	public int addKTDetails(KTPlan entity) throws DaoException {
		LOG.debug("Begin: KTPlanDAOImpl.addKTDetails");
		int createdRow=0;
		try{
			createdRow=abstractHibernateDAOAPI.saveEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: KTPlanDAOImpl.addKTDetails");
		return createdRow;
	}
	
	@Override
	public void updateKTDetails(KTPlan entity) throws DaoException {
		LOG.debug("Begin: KTPlanDAOImpl.updateKTDetails");
		try{
			abstractHibernateDAOAPI.updateEntity(entity);
		}catch(DaoException e){
			throw new DaoException(e.getFaultCode(), e);
		}catch(Exception e){
			throw new DaoException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		LOG.debug("End: KTPlanDAOImpl.updateKTDetails");
	}

	@Override
	public void deleteData(List<Integer> deleteRecordList) throws DaoException{
		abstractHibernateDAOAPI.bulkSQLNativeOperation(buildDeleteQuery(deleteRecordList));
	}
	
	private List<String> buildDeleteQuery(List<Integer> deleteRecordList){
		LOG.debug("Begin: KTPlanDAOImpl.buildDeleteQuery");
		StringBuffer queryBuffer = new StringBuffer();
		List<String> queryList = new ArrayList<>();
		if(null !=deleteRecordList && !deleteRecordList.isEmpty()){
			queryBuffer.append("UPDATE KTTable SET is_deleted = 1 WHERE tri_id in (");
			for (Object i:deleteRecordList ){
				queryBuffer.append(i.toString()+",");
			}
			queryBuffer.deleteCharAt(queryBuffer.length() -1);
		}
		queryBuffer.append(")");
		queryList.add(queryBuffer.toString());
		LOG.debug("End: KTPlanDAOImpl.buildDeleteQuery");
		return queryList;
	}
}
