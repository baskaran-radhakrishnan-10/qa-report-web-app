package com.equiniti.qa_report.dao.api.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.equiniti.qa_report.dao.api.ReportSearchDAO;
import com.equiniti.qa_report.entity.BtpEntity;
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
	
	public List<Map<String,Object>> getBtpWeeklyReportData(Map<String,Object> paramMap){
		List<Map<String,Object>> returnObj=null;
		return returnObj;
	}
	
	public List<Map<String,Object>> getBtpMonthlyReportData(Map<String,Object> paramMap){
		List<Map<String,Object>> returnObj=null;
		return returnObj;
	}

}
