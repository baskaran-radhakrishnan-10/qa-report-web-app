package com.equiniti.qa_report.dao.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.equiniti.qa_report.dao.api.ReportSearchDAO;
import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;
import com.equiniti.qa_report.util.ApplicationConstants;

@Repository(value="reportSearchDAO")
public class ReportSearchDAOImpl implements ReportSearchDAO{
	
	private static final Logger LOG = Logger.getLogger(ReportSearchDAOImpl.class);
	
	private AbstractHibernateDAOAPI<BtpEntity> abstractHibernateDAOAPI;
	
	public void setAbstractHibernateDAOAPI(AbstractHibernateDAOAPI<BtpEntity> abstractHibernateDAOAPI) {
		this.abstractHibernateDAOAPI = abstractHibernateDAOAPI;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getBtpSummaryReportData(Map<String,Object> paramMap) throws DaoException{
		List<Map<String,Object>> returnList=null;
		String dynamicQuery=null;
		if(null != paramMap && !paramMap.isEmpty()){
			Map<String,String> queryMap=new LinkedHashMap<>();
			queryMap.put("BTP_SUMMARY_REPORT_QUERY", "BTP_SUMMARY_REPORT_QUERY");
			dynamicQuery=abstractHibernateDAOAPI.constructQuery(queryMap);
			dynamicQuery=dynamicQuery.replace("BTP_NO_LIST", paramMap.get("BTP_NO_LIST").toString());
			returnList=(List<Map<String,Object>>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.SQL, dynamicQuery);
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getSelectedBtpReportData(Map<String,Object> paramMap)throws DaoException{
		List<Map<String,Object>> returnList=null;
		String dynamicQuery=null;
		if(null != paramMap && !paramMap.isEmpty()){
			Map<String,String> queryMap=new LinkedHashMap<>();
			String queryKey = (String) paramMap.get(ApplicationConstants.QUERY_KEY);
			queryMap.put(queryKey, queryKey);
			dynamicQuery=abstractHibernateDAOAPI.constructQuery(queryMap);
			paramMap.remove(ApplicationConstants.QUERY_KEY);
			dynamicQuery=dynamicQuery.replace("BTP_NO", paramMap.get("btpNo").toString());
			returnList=(List<Map<String,Object>>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.SQL, dynamicQuery);
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,List<Map<String,Object>>> getBTPMontlyReportData(Map<String,Object> paramMap)throws DaoException{
		Map<String,List<Map<String,Object>>> returnObj=null;
		String dynamicQuery=null;
		if(null != paramMap && !paramMap.isEmpty()){
			returnObj = new HashMap<>();
			Map<String,String> queryMap=new LinkedHashMap<>();
			queryMap.put("BTP_MONTHLY_REPORT_QUERY1", "BTP_MONTHLY_REPORT_QUERY1");
			dynamicQuery=abstractHibernateDAOAPI.constructQuery(queryMap);
			dynamicQuery=dynamicQuery.replace("START_DATE", "'"+paramMap.get("startDate").toString()+"'");
			dynamicQuery=dynamicQuery.replace("END_DATE", "'"+paramMap.get("endDate").toString()+"'");
			List<Map<String,Object>> btpItemDetailsDataList = (List<Map<String,Object>>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.SQL, dynamicQuery);
			if(null != btpItemDetailsDataList && !btpItemDetailsDataList.isEmpty()){
				returnObj.put("BTP_ITEM_DETAILS",btpItemDetailsDataList);
			}
			queryMap=new LinkedHashMap<>();
			queryMap.put("BTP_MONTHLY_REPORT_QUERY2", "BTP_MONTHLY_REPORT_QUERY2");
			dynamicQuery=abstractHibernateDAOAPI.constructQuery(queryMap);
			dynamicQuery=dynamicQuery.replace("START_DATE", "'"+paramMap.get("startDate").toString()+"'");
			dynamicQuery=dynamicQuery.replace("END_DATE", "'"+paramMap.get("endDate").toString()+"'");
			List<Map<String,Object>> btpResDetailsDataList = (List<Map<String,Object>>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.SQL, dynamicQuery);
			if(null != btpResDetailsDataList && !btpResDetailsDataList.isEmpty()){
				returnObj.put("BTP_RESOURCE_DETAILS",btpResDetailsDataList);
			}
		}
		return returnObj;
	}
	
	public List<Map<String,Object>> getBtpMonthlyReportData(Map<String,Object> paramMap){
		List<Map<String,Object>> returnObj=null;
		return returnObj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> getUserReportData(Map<String,Object> restrictionMap) throws DaoException{
		StringBuffer queryBuffer=new StringBuffer();
		Set<String> keySet = restrictionMap.keySet();
		List<String> keyList=new ArrayList<>(keySet);
		queryBuffer.append("SELECT btp.projectname,resource.acttime as actualTimeTaken,resource.resourcename FROM ResourceTable resource,BtpTable btp WHERE btp.btpno = resource.btpno AND ");
		for(int index = 0;index<keyList.size();index++){
			String key = keyList.get(index);
			if(key.indexOf("startDate") != -1){
				queryBuffer.append("btp.startdate").append(" >= '").append(restrictionMap.get(key)).append("'");
			}else if(key.indexOf("endDate") != -1){
				queryBuffer.append("btp.enddate").append(" <= '").append(restrictionMap.get(key)).append("'");
			}else if(key.indexOf("projectName") != -1){
				queryBuffer.append("btp.projectname").append(" = '").append(restrictionMap.get(key)).append("'");
			}else if(key.indexOf("resourceName") != -1){
				queryBuffer.append("resource.resourcename").append(" = '").append(restrictionMap.get(key)).append("'");
			}
			if(index < (keyList.size()-1)){
				queryBuffer.append(" AND ");
			}
		}
		return (List<Map<String,Object>>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.SQL, queryBuffer.toString());
	}

}
