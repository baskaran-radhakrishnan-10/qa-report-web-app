package com.equiniti.qa_report.dao.api.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.equiniti.qa_report.dao.api.ReportSearchDAO;
import com.equiniti.qa_report.entity.BtpEntity;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.exception.api.exception.DaoException;
import com.equiniti.qa_report.persistance_api.consenum.QueryOperationType;
import com.equiniti.qa_report.persistance_api.consenum.QueryType;
import com.equiniti.qa_report.persistance_api.hibernate.api.AbstractHibernateDAOAPI;

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
			queryMap.put("SELECTED_BTP_REPORT_QUERY", "SELECTED_BTP_REPORT_QUERY");
			dynamicQuery=abstractHibernateDAOAPI.constructQuery(queryMap);
			dynamicQuery=dynamicQuery.replace("BTP_NO", paramMap.get("btpNo").toString());
			returnList=(List<Map<String,Object>>) abstractHibernateDAOAPI.processQuery(null, null, null, QueryOperationType.SELECT, QueryType.SQL, dynamicQuery);
		}
		return returnList;
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
