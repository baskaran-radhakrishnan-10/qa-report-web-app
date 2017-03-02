package com.equiniti.qa_report.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.exception.api.util.CommonFileUtil;
import com.equiniti.qa_report.util.ApplicationConstants;

@Service
public class ReportExportHandler {

	private static final Logger LOG = Logger.getLogger(ReportExportHandler.class);

	private static final String BTP_SUMMARY_REPORT_TEMPLATE_PATH="empty-templates/BTP_BUILD_SUMMARY_XLS_TEMPLATE.xlsx";
	private static final String BTP_WEEKLY_REPORT_TEMPLATE_PATH="empty-templates/BTP_WEEKLY_REPORT_XLS_TEMPLATE.xlsx";
	private static final String BTP_MONTHLY_REPORT_TEMPLATE_PATH="empty-templates/BTP_MONTHLY_REPORT_XLS_TEMPLATE.xlsx";
	private static final String BTP_SELECTED_ROW_REPORT_TEMPLATE_PATH="empty-templates/SELECTED_BTP_DETAIL_XLS_TEMPLATE.xlsx";
	private static final String DSR_SUMMARY_REPORT_TEMPLATE_PATH="empty-templates/DSR_BUILD_SUMMARY_XLS_TEMPLATE.xlsx";
	private static final String DSR_SELECTED_ROW_REPORT_TEMPLATE_PATH="empty-templates/SELECTED_DSR_DETAIL_XLS_TEMPLATE.xlsx";
	private static final String USER_SUMMARY_REPORT_TEMPLATE_PATH="empty-templates/USER_BUILD_SUMMARY_XLS_TEMPLATE.xlsx";
	
	private static final String BTP_SUMMARY_REPORT_OUTPUT="BTPSummaryReport.xlsx";
	private static final String BTP_WEEKLY_REPORT_OUTPUT="BTPWeeklyReport.xlsx";
	private static final String BTP_MONTHLY_REPORT_OUTPUT="BTPMonthlyReport.xlsx";
	private static final String BTP_SELECTED_ROW_OUTPUT="BTPSelectedRowReport.xlsx";
	private static final String DSR_SUMMARY_REPORT_OUTPUT="DSRSummaryReport.xlsx";
	private static final String DSR_SELECTED_ROW_OUTPUT="DSRDayReport.xlsx";
	private static final String USER_SUMMARY_REPORT_OUTPUT="UserSummaryReport.xlsx";

	private static final String PROJECT_CODE="PROJECT_CODE";
	private static final String BTP_NO="BTP_NO";
	private static final String PHASE="PHASE";
	private static final String S_NO="S_NO";
	private static final String BUILD_ID="BUILD_ID";
	private static final String START_DATE="START_DATE";
	private static final String END_DATE="END_DATE";
	private static final String REV_END_DATE="REV_END_DATE";
	private static final String EST_HRS="EST_HRS";
	private static final String ACT_HRS="ACT_HRS";
	private static final String RESOURCE_COUNT="RESOURCE_COUNT";
	private static final String BUGS_ASSIGNED="BUGS_ASSIGNED";
	private static final String BUGS_LOGGED="BUGS_LOGGED";
	private static final String US_TESTED="US_TESTED";
	private static final String STATUS="STATUS";
	private static final String REMARKS="REMARKS";
	private static final String TASK="TASK";
	private static final String ESTIMATED="ESTIMATED";
	private static final String COMPLETED="COMPLETED";
	private static final String REMAINING="REMAINING";
	private static final String PERCENTAGE="PERCENTAGE";
	private static final String RESOURCE="RESOURCE";
	private static final String IS_ROW_PROC="IS_ROW_PROC";
	private static final String ITEM_DESC="ITEM_DESC";
	private static final String ITEM_COUNT="ITEM_COUNT";
	private static final String ITEM_DETAIL="ITEM_DETAIL";
	private static final String CYCLE = "CYCLE";
	private static final String BTP_PLAN = "BTP_PLAN" ;
	
	private static final String[] monthArray = {"January","Feburary","March","April","May","June","July","August","September","October","November","December"};
	
	private CacheInstance cache =null;
	
	public ReportExportHandler() throws CacheException{
		this.cache= CacheInstance.getInstance();
	}

	@SuppressWarnings("unchecked")
	public void exportDSRReport(Map<String, Object> exportDataMap) throws CacheException{

		String reportType=(String) exportDataMap.get(ApplicationConstants.REPORT_TYPE);

		String userId=(String) exportDataMap.get("USER_ID");

		Map<Integer,List<DSREntity>> dsrDataMap=  (Map<Integer,List<DSREntity>>) exportDataMap.get(ApplicationConstants.REPORT_DATA);

		Map<String,Object> exportMap = new HashMap<>();
		exportMap.put("XLS_DATA", dsrDataMap);
		exportMap.put("OUTPUT_FILE_NAME", dsrDataMap.size() > 1 ? ReportExportHandler.DSR_SELECTED_ROW_OUTPUT: ReportExportHandler.DSR_SUMMARY_REPORT_OUTPUT);
		exportMap.put("USER_ID", userId);
		exportMap.put("TEMPLATE_FILE_PATH", dsrDataMap.size() > 1 ? ReportExportHandler.DSR_SELECTED_ROW_REPORT_TEMPLATE_PATH : ReportExportHandler.DSR_SUMMARY_REPORT_TEMPLATE_PATH);
		exportMap.put("ROW_NO", dsrDataMap.size() > 1 ? 2 : 1);
		exportMap.put("EXPORT_TYPE", reportType);
		exportXLSFile(exportMap);

	}

	@SuppressWarnings("unchecked")
	public void exportUserReport(Map<String, Object> exportDataMap) throws CacheException{

		String reportType=(String) exportDataMap.get(ApplicationConstants.REPORT_TYPE);

		String userId=(String) exportDataMap.get("USER_ID");

		List<Map<String,Object>> reportDataObj=  (List<Map<String, Object>>) exportDataMap.get(ApplicationConstants.REPORT_DATA);

		Map<String,Object> exportMap = new HashMap<>();
		exportMap.put("XLS_DATA", reportDataObj);
		exportMap.put("OUTPUT_FILE_NAME", ReportExportHandler.USER_SUMMARY_REPORT_OUTPUT);
		exportMap.put("USER_ID", userId);
		exportMap.put("TEMPLATE_FILE_PATH", ReportExportHandler.USER_SUMMARY_REPORT_TEMPLATE_PATH );
		exportMap.put("ROW_NO", 1);
		exportMap.put("EXPORT_TYPE", reportType);
		exportXLSFile(exportMap);

	}

	private void updateItemDetails(List<Map<String,Object>> btpItemDetailsList,String btpKey,Map<String,Object> internalObject){

		for(Map<String,Object> dataMap : btpItemDetailsList){

			String projectName = String.valueOf(dataMap.get("projectname"));
			
			int btpNo = Integer.valueOf(String.valueOf(dataMap.get("btpno")));
			
			String key = new StringBuffer().append(projectName).append("~~~").append(btpNo).toString();

			if(btpKey.intern() == key.intern()){

				String itemDescription = String.valueOf(dataMap.get("itemdescription"));

				int itemCount = (int) dataMap.get("itemcount");

				itemDescription = itemDescription.indexOf("null") != -1 ? "" : itemDescription;

				if(itemDescription.indexOf("US-Retesting") != -1){

					if(internalObject.containsKey("US_RETEST")){

						itemCount += (Integer)internalObject.get("US_RETEST");

					}

					internalObject.put("US_RETEST", itemCount);

				}else if(itemDescription.indexOf("US-Regression") != -1){

					if(internalObject.containsKey("US_REGRESSION")){

						itemCount += (Integer)internalObject.get("US_REGRESSION");

					}

					internalObject.put("US_REGRESSION", itemCount);

				}else if(itemDescription.indexOf("US-Execution") != -1){

					if(internalObject.containsKey("US_EXECUTION")){

						itemCount += (Integer)internalObject.get("US_EXECUTION");

					}

					internalObject.put("US_EXECUTION", itemCount);

				}else if(itemDescription.indexOf("Test Design") != -1){

					if(internalObject.containsKey("TEST_DESIGN")){

						itemCount += (Integer)internalObject.get("TEST_DESIGN");

					}

					internalObject.put("TEST_DESIGN", itemCount);

				}else if(itemDescription.indexOf("Test Review") != -1){

					if(internalObject.containsKey("TEST_REVIEW")){

						itemCount += (Integer)internalObject.get("TEST_REVIEW");

					}

					internalObject.put("TEST_REVIEW", itemCount);

				}

			}

		}

	}

	private void updateResourceDetails(List<Map<String,Object>> btpResourceDetailsList,String btpKey,Map<String,Object> internalObject){

		for(Map<String,Object> dataMap : btpResourceDetailsList){

			String projectName = String.valueOf(dataMap.get("projectname"));
			
			int btpNo = Integer.valueOf(String.valueOf(dataMap.get("btpno")));
			
			String key = new StringBuffer().append(projectName).append("~~~").append(btpNo).toString();

			if(btpKey.intern() == key.intern()){

				String itemName = String.valueOf(dataMap.get("itemname"));

				itemName = itemName.indexOf("null") != -1 ? "" : itemName;

				if(itemName.indexOf("Bugs Verification") != -1){

					if(internalObject.containsKey("BUGS_CLOSED")){

						int bugsClosedCount = (Integer)internalObject.get("BUGS_CLOSED")+(int) dataMap.get("pass");

						internalObject.put("BUGS_CLOSED", bugsClosedCount);

					}

				}

				if(internalObject.containsKey("BUGS_LOGGED")){

					int bugsLoggedCount = (Integer)internalObject.get("BUGS_LOGGED") + (int) dataMap.get("bugslogged");

					internalObject.put("BUGS_LOGGED", bugsLoggedCount);

				}

			}

		}

	}

	@SuppressWarnings("unchecked")
	public void exportBTPMonthlyReport(Map<String, Object> exportDataMap) throws CacheException{

		String reportType=(String) exportDataMap.get(ApplicationConstants.REPORT_TYPE);

		String userId=(String) exportDataMap.get("USER_ID");

		Map<String,List<Map<String,Object>>> dataObj = (Map<String,List<Map<String,Object>>>) exportDataMap.get(ApplicationConstants.REPORT_DATA);

		List<Map<String,Object>> btpItemDetailsList = dataObj.get("BTP_ITEM_DETAILS");

		List<Map<String,Object>> btpResourceDetailsList = dataObj.get("BTP_RESOURCE_DETAILS");

		Map<String,Map<String,Object>> updateObjectMap = new TreeMap<>();

		for(Map<String,Object> dataMap : btpItemDetailsList){

			int bugsReleasedCount = 0 , bugsLoggedCount = 0 , bugsClosedCount = 0 ;

			String projectName = String.valueOf(dataMap.get("projectname"));
			
			int btpNo = Integer.valueOf(String.valueOf(dataMap.get("btpno")));
			
			String key = new StringBuffer().append(projectName).append("~~~").append(btpNo).toString();

			String btpPlan = String.valueOf(dataMap.get("btpplan"));

			String cycle = String.valueOf(dataMap.get("cycle"));

			String buildNo = String.valueOf(dataMap.get("buildno"));

			String build = new StringBuffer().append(cycle).append("-").append(buildNo).toString();

			String btpRemarks = new StringBuffer(String.valueOf(dataMap.get("btpremarks"))).toString();
			
			String reportMonth = String.valueOf(dataMap.get("report_month"));
			
			String reportYear = String.valueOf(dataMap.get("report_year"));

			btpRemarks = btpRemarks.indexOf("null") != -1 ? "" : btpRemarks;

			Map<String,Object> internalObject = null;

			if(!updateObjectMap.containsKey(key)){

				internalObject = new LinkedHashMap<>();

				internalObject.put("PROJECT", projectName);
				
				internalObject.put("BUILD_COUNT", bugsReleasedCount);

				internalObject.put("BUILD_DETAILS", "");

				internalObject.put("BUGS_CLOSED", bugsClosedCount);

				internalObject.put("BUGS_LOGGED", bugsLoggedCount);
				
				internalObject.put("US", 0);

				internalObject.put("US_RETEST", 0);

				internalObject.put("US_REGRESSION", 0);

				internalObject.put("US_EXECUTION", 0);
				
				internalObject.put("TEST", 0);

				internalObject.put("TEST_DESIGN", 0);

				internalObject.put("TEST_REVIEW", 0);
				
				internalObject.put("REMARKS", "");
				
				internalObject.put("REPORT_MONTH", reportMonth);

				internalObject.put("REPORT_YEAR", reportYear);

				if("Build Test Plan".intern() ==  btpPlan.intern()){

					if(internalObject.containsKey("BUILD_COUNT")){

						bugsReleasedCount = (Integer)internalObject.get("BUILD_COUNT");

						bugsReleasedCount++;

					}

					internalObject.put("BUILD_COUNT", bugsReleasedCount);

				}
				
				if(internalObject.containsKey("BUILD_DETAILS")){

					String buildDetails = new StringBuffer(String.valueOf(internalObject.get("BUILD_DETAILS"))).append(build).toString();

					internalObject.put("BUILD_DETAILS", buildDetails);

				}
				
				if(internalObject.containsKey("REMARKS")){
					
					String buildDetails = new StringBuffer(String.valueOf(internalObject.get("REMARKS"))).append(btpRemarks).toString();
					
					internalObject.put("REMARKS", buildDetails);
					
				}

				updateItemDetails(btpItemDetailsList, key, internalObject);

				updateResourceDetails(btpResourceDetailsList, key, internalObject);
				
				StringBuffer detBuffer=new StringBuffer();
				
				detBuffer.append("US-Execution = ").append(internalObject.get("US_EXECUTION")).append(";");
				
				detBuffer.append("US-Retesting = ").append(internalObject.get("US_RETEST")).append(";");
				
				detBuffer.append("US-Regression = ").append(internalObject.get("US_REGRESSION")).append(";");
				
				internalObject.put("US", detBuffer.toString());
				
				detBuffer=new StringBuffer();
				
				detBuffer.append("Test-Design = ").append(internalObject.get("TEST_DESIGN")).append(";");
				
				detBuffer.append("Test-Review = ").append(internalObject.get("TEST_REVIEW")).append(";");
				
				internalObject.put("TEST", detBuffer.toString());
				
				internalObject.remove("US_RETEST");
				
				internalObject.remove("US_REGRESSION");
				
				internalObject.remove("US_EXECUTION");
				
				internalObject.remove("TEST_DESIGN");

				internalObject.remove("TEST_REVIEW");
				
				updateObjectMap.put(key, internalObject);

			}
			
		}

		LOG.debug(updateObjectMap);
		
		if(!updateObjectMap.isEmpty()){
			Map<String,Object> exportMap = new HashMap<>();
			exportMap.put("XLS_DATA", updateObjectMap);
			exportMap.put("OUTPUT_FILE_NAME", ReportExportHandler.BTP_MONTHLY_REPORT_OUTPUT);
			exportMap.put("USER_ID", userId);
			exportMap.put("TEMPLATE_FILE_PATH", ReportExportHandler.BTP_MONTHLY_REPORT_TEMPLATE_PATH);
			exportMap.put("ROW_NO", 2);
			exportMap.put("EXPORT_TYPE", reportType);
			exportXLSFile(exportMap);
		}

	}

	@SuppressWarnings("unchecked")
	public void exportBTPReport(Map<String, Object> exportDataMap) throws CacheException{

		String reportType=(String) exportDataMap.get(ApplicationConstants.REPORT_TYPE);

		String userId=(String) exportDataMap.get("USER_ID");

		List<Map<String,Object>> dataObj = (List<Map<String, Object>>) exportDataMap.get(ApplicationConstants.REPORT_DATA);

		boolean isBTPWeeklyReport= (ApplicationConstants.BTP_WEEKLY_REPORT.intern() == reportType.intern());

		Map<Integer,Map<String,Object>> updateObjectMap=new TreeMap<>();

		Map<String,Object> btpDetailsMap=new HashMap<>();

		int sNo = 1;

		for(Map<String,Object> dataMap : dataObj){

			LOG.debug(dataMap);

			if(ApplicationConstants.SELECTED_BTP_REPORT.intern() == reportType.intern()){

				constructBTPDetailsMap(dataMap, btpDetailsMap);

				continue;
			}

			Map<String,Object> rowMap ;

			int totalResCount = 0, estEffortCount = 0, actualEffort = 0, bugsLoggedCount = 0, bugsAssinedCount = 0, usTestedCount = 0;

			int btpNo = Integer.valueOf(String.valueOf(dataMap.get("btpno")));

			StringBuffer resourceNameBuffer= isBTPWeeklyReport ? new StringBuffer() : null;

			StringBuffer taskBuffer=new StringBuffer();

			if(updateObjectMap.containsKey(btpNo)){
				rowMap=updateObjectMap.get(btpNo);
			}else{
				if(isBTPWeeklyReport){
					updateExsistingData(updateObjectMap);
				}
				rowMap= isBTPWeeklyReport? btpWeeklyReportHeaders() : btpSummaryReportHeaders();
				rowMap.put(ReportExportHandler.S_NO, sNo);
				sNo++;
			}

			if(null ==  rowMap.get(ReportExportHandler.PROJECT_CODE)){
				rowMap.put(ReportExportHandler.PROJECT_CODE, dataMap.get("projectname"));
			}

			if(null ==  rowMap.get(ReportExportHandler.START_DATE)){
				String startDate=((Timestamp)dataMap.get("startdate")).toString();
				rowMap.put(ReportExportHandler.START_DATE, startDate.substring(0, startDate.indexOf(" ")));
			}

			if(null ==  rowMap.get(ReportExportHandler.END_DATE)){
				String endDate=((Timestamp)dataMap.get("enddate")).toString();
				rowMap.put(ReportExportHandler.END_DATE, endDate.substring(0, endDate.indexOf(" ")));
			}

			if(null ==  rowMap.get(ReportExportHandler.STATUS)){
				rowMap.put(ReportExportHandler.STATUS, dataMap.get("btpstatus"));
			}

			if(null ==  rowMap.get(ReportExportHandler.REMARKS)){
				String remarks = (String) dataMap.get("btpremarks");
				rowMap.put(ReportExportHandler.REMARKS, remarks);
			}

			Set<String> keySet = dataMap.keySet();

			for(String key : keySet){

				if(key.indexOf("resource") != -1){
					String resource = (String) dataMap.get(key);
					if(null != resource && !resource.isEmpty()){
						if(isBTPWeeklyReport){
							resourceNameBuffer.append(resource).append(",");
						}else{
							totalResCount ++;
						}
					}
				}else if(key.indexOf("acteffort") != -1){

					actualEffort += ((Double) dataMap.get(key)).intValue();

					rowMap.put(isBTPWeeklyReport ? ReportExportHandler.COMPLETED : ReportExportHandler.ACT_HRS,actualEffort);

				}else if(key.indexOf("esteffort") != -1){

					estEffortCount += ((Double) dataMap.get(key)).intValue();

					rowMap.put(isBTPWeeklyReport ? ReportExportHandler.ESTIMATED : ReportExportHandler.EST_HRS,estEffortCount);

				}
				else if(!isBTPWeeklyReport && key.indexOf("bugslogged") != -1){

					bugsLoggedCount += (Integer)dataMap.get(key);

					rowMap.put(ReportExportHandler.BUGS_LOGGED,bugsLoggedCount);

				}else if(!isBTPWeeklyReport && key.indexOf("itemcount") != -1){

					if(keySet.contains("itemdescription")){

						String itemDescription=(String)dataMap.get("itemdescription");

						if(itemDescription.indexOf("Bugs Verification") != -1){

							bugsAssinedCount += (Integer)dataMap.get(key);

							rowMap.put(ReportExportHandler.BUGS_ASSIGNED,bugsAssinedCount);

						}else if(itemDescription.indexOf("US") != -1){

							usTestedCount += (Integer)dataMap.get(key);

							rowMap.put(ReportExportHandler.US_TESTED,usTestedCount);

						}

					}
				}

			}

			if(isBTPWeeklyReport){

				taskBuffer.append((String)dataMap.get("itemdescription")).append("-").append((Integer)dataMap.get("itemcount")).append(",");

				if(null == rowMap.get(ReportExportHandler.TASK)){
					rowMap.put(ReportExportHandler.TASK, taskBuffer.toString());
				}else{
					StringBuffer exsTaskBuufer=new StringBuffer((String)rowMap.get(ReportExportHandler.TASK));
					exsTaskBuufer.append(taskBuffer.toString());
					rowMap.put(ReportExportHandler.TASK, exsTaskBuufer.toString());
				}

				if(null == rowMap.get(ReportExportHandler.RESOURCE)){
					String resourceName = resourceNameBuffer.toString();
					rowMap.put(ReportExportHandler.RESOURCE, resourceName.substring(0, resourceName.lastIndexOf(",")));
				}

			}

			if(!isBTPWeeklyReport){

				if(null == rowMap.get(ReportExportHandler.BUILD_ID)){
					rowMap.put(ReportExportHandler.BUILD_ID, dataMap.get("projectname") + "_" + dataMap.get("phase") + "_" + dataMap.get("cycle") + "_" + dataMap.get("buildno"));
				}

				if(null == rowMap.get(ReportExportHandler.RESOURCE_COUNT)){
					rowMap.put(ReportExportHandler.RESOURCE_COUNT,totalResCount);
				}

				if(null == rowMap.get(ReportExportHandler.REV_END_DATE)){
					rowMap.put(ReportExportHandler.REV_END_DATE, "");
					if(null != dataMap.get("revisedenddate")){
						String revEndDate=((Timestamp)dataMap.get("revisedenddate")).toString();
						rowMap.put(ReportExportHandler.REV_END_DATE, revEndDate.substring(0, revEndDate.indexOf(" ")));
					}
				}

			}

			updateObjectMap.put(btpNo, rowMap);

		}

		if(!btpDetailsMap.isEmpty()){
			Map<String,Object> exportMap = new HashMap<>();
			exportMap.put("XLS_DATA", btpDetailsMap);
			exportMap.put("OUTPUT_FILE_NAME", ReportExportHandler.BTP_SELECTED_ROW_OUTPUT);
			exportMap.put("USER_ID", userId);
			exportMap.put("TEMPLATE_FILE_PATH", ReportExportHandler.BTP_SELECTED_ROW_REPORT_TEMPLATE_PATH);
			exportMap.put("EXPORT_TYPE", reportType);
			exportXLSFile(exportMap);
		}

		if(!updateObjectMap.isEmpty()){
			Map<String,Object> exportMap = new HashMap<>();
			exportMap.put("XLS_DATA", updateObjectMap);
			exportMap.put("OUTPUT_FILE_NAME", isBTPWeeklyReport ? ReportExportHandler.BTP_WEEKLY_REPORT_OUTPUT : ReportExportHandler.BTP_SUMMARY_REPORT_OUTPUT);
			exportMap.put("USER_ID", userId);
			exportMap.put("TEMPLATE_FILE_PATH", isBTPWeeklyReport ? ReportExportHandler.BTP_WEEKLY_REPORT_TEMPLATE_PATH : ReportExportHandler.BTP_SUMMARY_REPORT_TEMPLATE_PATH);
			exportMap.put("ROW_NO", isBTPWeeklyReport ? 2 : 1);
			exportMap.put("EXPORT_TYPE", reportType);
			exportXLSFile(exportMap);
		}

	}

	@SuppressWarnings({ "unchecked" })
	private void constructBTPDetailsMap(Map<String,Object> dataMap,Map<String,Object> btpDetailsMap){

		int btpNo = Integer.valueOf(String.valueOf(dataMap.get("btpno")));
		if(!btpDetailsMap.containsKey("BTP_NO")){

			btpDetailsMap.put(ReportExportHandler.BTP_NO, btpNo);
			btpDetailsMap.put(ReportExportHandler.PROJECT_CODE, dataMap.get("projectname"));
			btpDetailsMap.put(ReportExportHandler.PHASE, dataMap.get("phase"));
			btpDetailsMap.put(ReportExportHandler.BUILD_ID, dataMap.get("buildno"));
			btpDetailsMap.put(ReportExportHandler.STATUS, dataMap.get("btpstatus"));
			btpDetailsMap.put(ReportExportHandler.CYCLE, dataMap.get("cycle"));
			btpDetailsMap.put(ReportExportHandler.BTP_PLAN, dataMap.get("btpplan"));

			String startDate=((Timestamp)dataMap.get("startdate")).toString();
			btpDetailsMap.put(ReportExportHandler.START_DATE, startDate.substring(0, startDate.indexOf(" ")));

			String endDate=((Timestamp)dataMap.get("enddate")).toString();
			btpDetailsMap.put(ReportExportHandler.END_DATE, endDate.substring(0, endDate.indexOf(" ")));

			if(null != dataMap.get("revisedenddate")){
				String revEndDate=((Timestamp)dataMap.get("revisedenddate")).toString();
				btpDetailsMap.put(ReportExportHandler.REV_END_DATE, revEndDate.substring(0, revEndDate.indexOf(" ")));
			}


			Set<String> keySet=dataMap.keySet();
			for(String key : keySet){
				if(key.indexOf("resource") != -1){
					if(null != dataMap.get(key)){
						btpDetailsMap.put(key, dataMap.get(key));
					}
				}
			}
			
			if(dataMap.containsKey("itemdescription") && dataMap.containsKey("itemcount") &&  dataMap.containsKey("esteffort")){
				List<Map<String,Object>> itemDetailsList=new ArrayList<>();
				Map<String,Object> itemDetailsMap=new HashMap<>();
				itemDetailsMap.put(ReportExportHandler.ITEM_DESC, dataMap.get("itemdescription"));
				itemDetailsMap.put(ReportExportHandler.ITEM_COUNT, dataMap.get("itemcount"));
				itemDetailsMap.put(ReportExportHandler.EST_HRS, dataMap.get("esteffort"));
				itemDetailsMap.put(ReportExportHandler.ACT_HRS, dataMap.get("acteffort"));
				itemDetailsMap.put(ReportExportHandler.STATUS, dataMap.get("itemstatus"));
				itemDetailsMap.put(ReportExportHandler.REMARKS, dataMap.get("itemremarks"));
				itemDetailsList.add(itemDetailsMap);
				btpDetailsMap.put(ReportExportHandler.ITEM_DETAIL, itemDetailsList);
			}

		}else{
			List<Map<String,Object>> itemDetailsList=(List<Map<String, Object>>) btpDetailsMap.get(ReportExportHandler.ITEM_DETAIL);
			Map<String,Object> itemDetailsMap=new HashMap<>();
			itemDetailsMap.put(ReportExportHandler.ITEM_DESC, dataMap.get("itemdescription"));
			itemDetailsMap.put(ReportExportHandler.ITEM_COUNT, dataMap.get("itemcount"));
			itemDetailsMap.put(ReportExportHandler.EST_HRS, dataMap.get("esteffort"));
			itemDetailsMap.put(ReportExportHandler.ACT_HRS, dataMap.get("acteffort"));
			itemDetailsMap.put(ReportExportHandler.STATUS, dataMap.get("itemstatus"));
			itemDetailsMap.put(ReportExportHandler.REMARKS, dataMap.get("itemremarks"));
			itemDetailsList.add(itemDetailsMap);
			btpDetailsMap.put(ReportExportHandler.ITEM_DETAIL, itemDetailsList);
		}
	}

	@SuppressWarnings("unchecked")
	private void exportXLSFile(Map<String,Object> exportDataMap) throws CacheException{

		String templateFilePath = (String) exportDataMap.get("TEMPLATE_FILE_PATH");
		String outputFileName = (String) exportDataMap.get("OUTPUT_FILE_NAME");
		String userId = (String) exportDataMap.get("USER_ID");
		String exportType = (String) exportDataMap.get("EXPORT_TYPE");

		InputStream inputStream = null ;
		Workbook workbook = null;
		try {
			inputStream=CommonFileUtil.getInputStream(templateFilePath, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(null != inputStream){

			try {
				if (templateFilePath.toLowerCase().endsWith(".xlsx")) {
					workbook = new XSSFWorkbook(inputStream);
				} else if (templateFilePath.toLowerCase().endsWith(".xls")) {
					workbook = new HSSFWorkbook(inputStream);
				} else{
					workbook=WorkbookFactory.create(inputStream);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}

			if(null != workbook){
				if(ApplicationConstants.BTP_WEEKLY_REPORT.intern() == exportType.intern() || ApplicationConstants.BTP_SUMMARY_REPORT.intern() == exportType.intern()){
					prepareBTPReportDocument(workbook, (Map<Integer, Map<String, Object>>) exportDataMap.get("XLS_DATA"), (int) exportDataMap.get("ROW_NO"));
				}else if(ApplicationConstants.SELECTED_BTP_REPORT == exportType.intern()){
					prepareBTPDeatilsExportDocument(workbook, (Map<String, Object>) exportDataMap.get("XLS_DATA"));
				}else if(ApplicationConstants.DSR_SUMMARY_REPORT == exportType.intern()){
					prepareDSRReportDocument(workbook, (Map<Integer,List<DSREntity>>) exportDataMap.get("XLS_DATA"), (int) exportDataMap.get("ROW_NO"));
				}else if(ApplicationConstants.USER_SUMMARY_REPORT == exportType.intern()){
					prepareUserReportDocument(workbook, (List<Map<String,Object>>) exportDataMap.get("XLS_DATA"), (int) exportDataMap.get("ROW_NO"));
				}else if(ApplicationConstants.BTP_MONTHLY_REPORT == exportType.intern()){
					prepareBTPMonthlyReportDocument(workbook, (Map<String,Map<String,Object>>) exportDataMap.get("XLS_DATA"), (int) exportDataMap.get("ROW_NO"));
				}
				writeExportedFile(userId, outputFileName, workbook);
				if(ApplicationConstants.BTP_MONTHLY_REPORT == exportType.intern()){
					this.cache.putItemInCache(ApplicationConstants.BTP_MONTHLY_REPORT_CONSTRUCTED, true, userId);
				}else if(ApplicationConstants.DSR_SUMMARY_REPORT == exportType.intern() && 2 == (int) exportDataMap.get("ROW_NO")){
					this.cache.putItemInCache(ApplicationConstants.DSR_DAILY_REPORT_CONSTRUCTED, true, userId);
				}
			}
		}
	}
	
	private void prepareBTPMonthlyReportDocument(Workbook workbook,Map<String,Map<String,Object>> xlsData,int startRowNo){
		
		int rownum = startRowNo;

		Sheet sheet = workbook.getSheetAt(0);
		
		Set<String> xlsKeySet = xlsData.keySet();
		
		for(String xlsKey : xlsKeySet){
			
			Map<String,Object> rowDataMap = xlsData.get(xlsKey);
			
			int cellnum = 0;
			
			if(startRowNo == rownum){
				
				String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
				
				String month = monthArray[Integer.valueOf(rowDataMap.get("REPORT_MONTH").toString())-1];
				
				String year = rowDataMap.get("REPORT_YEAR").toString();
				
				sheet.getRow(0).getCell(0).setCellValue(new StringBuffer().append(cellValue).append(" ").append(month).append("-").append(year).toString());
				
			}
			
			Row row = sheet.createRow(rownum++);
			
			Cell cell = row.createCell(cellnum++);

			setCellValue((rownum-2), cell);
			
			Set<String> rowDataKeySet = rowDataMap.keySet();
			
			for(String rowDataKey : rowDataKeySet){
				
				if(rowDataKey.indexOf("REPORT_") == -1){
					
					cell = row.createCell(cellnum++);
					
					setCellValue((rowDataMap.get(rowDataKey)), cell);
					
				}
				
			}
			
		}
		
	}

	private void prepareUserReportDocument(Workbook workbook,List<Map<String,Object>> xlsData,int startRowNo){

		int rownum = startRowNo;

		Sheet sheet = workbook.getSheetAt(0);

		for(Map<String,Object> rowData : xlsData){

			int cellnum = 0;

			Row row = sheet.createRow(rownum++);

			Cell cell = row.createCell(cellnum++);

			setCellValue((rownum-1), cell);

			cell = row.createCell(cellnum++);

			Object obj = rowData.get("projectName");

			setCellValue(obj, cell);

			cell = row.createCell(cellnum++);

			obj = rowData.get("resourceName");

			setCellValue(obj, cell);

			cell = row.createCell(cellnum++);

			obj = rowData.get("actualTimeTaken");

			setCellValue(obj, cell);

		}

	}

	private void prepareDSRReportDocument(Workbook workbook,Map<Integer,List<DSREntity>> xlsData,int startRowNo){

		int noOfSheets = workbook.getNumberOfSheets();

		for(int index = 0;index<noOfSheets;index++){
			
			int rownum = startRowNo;

			Sheet sheet = workbook.getSheetAt(index);

			List<DSREntity> entityList = xlsData.get(index);

			for(DSREntity entity : entityList){
				
				if(rownum == startRowNo && startRowNo > 1){
					
					String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
					
					DateTime dsrDate = entity.getDsrDate();
					
					String month = monthArray[Integer.valueOf(dsrDate.getMonthOfYear())-1];
					
					String year = String.valueOf(dsrDate.getYear());
					
					String day = String.valueOf(dsrDate.getDayOfMonth());
					
					sheet.getRow(0).getCell(0).setCellValue(new StringBuffer().append(cellValue).append(" ").append(day).append("-").append(month).append("-").append(year).toString());
					
				}

				Row row = sheet.createRow(rownum++);

				int cellnum = 0;
				
				Cell cell = row.createCell(cellnum++);

				setCellValue((rownum-startRowNo), cell);

				cell = row.createCell(cellnum++);

				Object obj =entity.getProjectName();

				setCellValue(obj, cell);

				cell = row.createCell(cellnum++);

				obj =entity.getResource();

				setCellValue(obj, cell);
				
				if(noOfSheets == 1){
					
					cell = row.createCell(cellnum++);

					obj =entity.getDsrDate();

					setCellValue(obj, cell);
					
				}

				cell = row.createCell(cellnum++);

				obj =entity.getPlannedTask();

				setCellValue(obj, cell);

				cell = row.createCell(cellnum++);

				obj =entity.getAccomplishedTask();

				setCellValue(obj, cell);

				cell = row.createCell(cellnum++);

				obj =entity.getDsrStatus();

				setCellValue(obj, cell);

				cell = row.createCell(cellnum++);

				obj =entity.getRemarks();

				setCellValue(obj, cell);
				
				if(noOfSheets == 1){
					
					cell = row.createCell(cellnum++);

					obj =entity.getSpentHours();

					setCellValue(obj, cell);

					cell = row.createCell(cellnum++);

					obj =entity.getPlannedHours();

					setCellValue(obj, cell);
					
				}

			}

		}

	}

	private void setCellValue(Object obj,Cell cell){

		if(obj instanceof Date) 
			cell.setCellValue((Date)obj);

		else if(obj instanceof Boolean)
			cell.setCellValue((Boolean)obj);

		else if(obj instanceof String)
			cell.setCellValue((String)obj);

		else if(obj instanceof Double)
			cell.setCellValue((Double)obj);

		else if(obj instanceof Integer)
			cell.setCellValue((Integer)obj);

		else if(obj instanceof Float)
			cell.setCellValue((Float)obj);

		else if(obj instanceof DateTime)
			cell.setCellValue(((DateTime)obj).toString("yyyy-MM-dd"));
	}


	private void prepareBTPReportDocument(Workbook workbook,Map<Integer,Map<String,Object>> xlsData,int startRowNo){

		int rownum = startRowNo;

		Sheet sheet = workbook.getSheetAt(0);

		Set<Integer> keySet = xlsData.keySet();

		for(int itemNo : keySet){

			int cellnum = 0;

			Row row = sheet.createRow(rownum++);

			Map<String,Object> rowDataMap = xlsData.get(itemNo);

			Set<String> columnNameKeySet = rowDataMap.keySet();

			for(String columnName : columnNameKeySet){

				if(ReportExportHandler.IS_ROW_PROC.intern() == columnName.intern()){

					continue;

				}

				Cell cell = row.createCell(cellnum++);

				Object obj =rowDataMap.get(columnName);

				setCellValue(obj, cell);

			}

		}
	}

	@SuppressWarnings("unchecked")
	private void prepareBTPDeatilsExportDocument(Workbook workbook,Map<String,Object> btpDetailsMap){

		StringBuffer buffer=new StringBuffer();

		buffer.append(btpDetailsMap.get(ReportExportHandler.PROJECT_CODE)).append(" - ").append(btpDetailsMap.get(ReportExportHandler.PHASE)).append(" - ");

		buffer.append(btpDetailsMap.get(ReportExportHandler.BTP_PLAN)).append(" - ").append(btpDetailsMap.get(ReportExportHandler.CYCLE));

		String sheetHeaderName=buffer.toString();

		String versionNo = (String) btpDetailsMap.get(ReportExportHandler.BUILD_ID);

		String status =  (String) btpDetailsMap.get(ReportExportHandler.STATUS);

		String startDate =  (String) btpDetailsMap.get(ReportExportHandler.START_DATE);

		String endDate =  (String) btpDetailsMap.get(ReportExportHandler.END_DATE);

		String revEndDate =  (String) btpDetailsMap.get(ReportExportHandler.REV_END_DATE);

		List<Map<String,Object>> itemDetailsList = (List<Map<String, Object>>) btpDetailsMap.get(ReportExportHandler.ITEM_DETAIL);

		Sheet sheet = workbook.getSheetAt(0);

		sheet.getRow(0).getCell(0).setCellValue(sheetHeaderName);

		sheet.getRow(1).getCell(1).setCellValue(versionNo);

		sheet.getRow(2).getCell(1).setCellValue(status);

		sheet.getRow(3).getCell(1).setCellValue(startDate);

		sheet.getRow(4).getCell(1).setCellValue(endDate);

		sheet.getRow(5).getCell(1).setCellValue(revEndDate);

		Set<String> keySet=btpDetailsMap.keySet();


		int resourceStartRow = 1 , resourceStartColumn = 3; 

		for(String key : keySet){

			if(key.indexOf("resource") != -1){

				String resource = null != btpDetailsMap.get(key) ? btpDetailsMap.get(key).toString() : "" ;

				if(!resource.isEmpty()){

					if(resourceStartColumn == 3){

						sheet.getRow(resourceStartRow).getCell(resourceStartColumn).setCellValue(resource);

						resourceStartColumn = 5;

					}else if(resourceStartColumn == 5){

						sheet.getRow(resourceStartRow).getCell(resourceStartColumn).setCellValue(resource);

						resourceStartColumn = 3;

						resourceStartRow++;

					}

				}

			}

		}

		int itemStartRow = 8 ;
		
		if(null == itemDetailsList || itemDetailsList.isEmpty()){
			
			itemStartRow = 6;
					
		}
		
		if(null != itemDetailsList){
			
			for(int index=0;index<(itemDetailsList.size());index++){
				
				Map<String,Object> itemDetailsMap=itemDetailsList.get(index);
				
				sheet.getRow(itemStartRow).getCell(1).setCellValue((String) itemDetailsMap.get(ITEM_DESC));

				sheet.getRow(itemStartRow).getCell(2).setCellValue((Integer)itemDetailsMap.get(ITEM_COUNT));

				sheet.getRow(itemStartRow).getCell(4).setCellValue((Double)itemDetailsMap.get(EST_HRS));

				sheet.getRow(itemStartRow).getCell(5).setCellValue((Double)itemDetailsMap.get(ACT_HRS));

				sheet.getRow(itemStartRow).getCell(6).setCellValue((String) itemDetailsMap.get(STATUS));

				sheet.getRow(itemStartRow).getCell(7).setCellValue((String) itemDetailsMap.get(REMARKS));
				
				if(index < (itemDetailsList.size() -1)){
					itemStartRow++;
				}
				
			}
			
		}

		while(itemStartRow < 22){
			
			sheet.removeRow(sheet.getRow(itemStartRow));

			itemStartRow++;

		}


	}

	private void updateExsistingData(Map<Integer,Map<String,Object>> updateObjectMap){

		Set<Integer> btpNoSet = updateObjectMap.keySet();

		for(int btpNo : btpNoSet){

			String percentage = null;

			Map<String,Object> rowMap = updateObjectMap.get(btpNo);

			if(null == rowMap.get(ReportExportHandler.IS_ROW_PROC)){

				float completed = (int) rowMap.get(ReportExportHandler.COMPLETED);

				float estimated = (int) rowMap.get(ReportExportHandler.ESTIMATED);

				if(completed > 0){
					completed = Float.valueOf(String.format("%.1f", (completed/7.5)));
				}

				if(completed > 0){
					estimated = Float.valueOf(String.format("%.1f", (estimated/7.5)));
				}

				float remaining  = Float.valueOf(String.format("%.1f", (estimated - completed)));

				if(completed > 0 && estimated > 0){
					percentage = calculateCompletedPercentage(completed, estimated);
				}

				rowMap.put(ReportExportHandler.COMPLETED, completed);

				rowMap.put(ReportExportHandler.ESTIMATED, estimated);

				rowMap.put(ReportExportHandler.REMAINING, remaining);

				rowMap.put(ReportExportHandler.PERCENTAGE, percentage);

				rowMap.put(ReportExportHandler.IS_ROW_PROC, true);
			}

		}
	}

	private void writeExportedFile(String userId , String outputFileName , Workbook workbook){

		try {

			LOG.debug("userId :"+userId);

			LOG.debug("outputFileName :"+outputFileName);

			File dir = new File(ApplicationConstants.APP_CONFIG_FOLDER_PATH+File.separator+userId);
			if (!dir.exists()){
				dir.mkdirs();
			}

			LOG.debug("dir :"+dir.getAbsolutePath());

			File serverFile = new File(dir.getAbsolutePath()+ File.separator +outputFileName);
			FileOutputStream outStream = new FileOutputStream(serverFile);
			workbook.write(outStream);
			outStream.close();

			LOG.debug("File Export Done Successfully!!!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String calculateCompletedPercentage(float completed,float estimated){

		float percentage = (completed/estimated) * 100 ;

		return String.format("%.2f", percentage);
	}

	private Map<String,Object> btpSummaryReportHeaders(){
		Map<String,Object> returnMap=new LinkedHashMap<>();
		returnMap.put(ReportExportHandler.S_NO, null);
		returnMap.put(ReportExportHandler.PROJECT_CODE, null);
		returnMap.put(ReportExportHandler.BUILD_ID, null);
		returnMap.put(ReportExportHandler.START_DATE, null);
		returnMap.put(ReportExportHandler.END_DATE, null);
		returnMap.put(ReportExportHandler.REV_END_DATE, null);
		returnMap.put(ReportExportHandler.EST_HRS, null);
		returnMap.put(ReportExportHandler.ACT_HRS, null);
		returnMap.put(ReportExportHandler.RESOURCE_COUNT, null);
		returnMap.put(ReportExportHandler.BUGS_ASSIGNED, null);
		returnMap.put(ReportExportHandler.BUGS_LOGGED, null);
		returnMap.put(ReportExportHandler.US_TESTED, null);
		returnMap.put(ReportExportHandler.STATUS, null);
		returnMap.put(ReportExportHandler.REMARKS, null);
		return returnMap;
	}

	private Map<String,Object> btpWeeklyReportHeaders(){
		Map<String,Object> returnMap=new LinkedHashMap<>();
		returnMap.put(ReportExportHandler.S_NO, null);
		returnMap.put(ReportExportHandler.PROJECT_CODE, null);
		returnMap.put(ReportExportHandler.TASK, null);
		returnMap.put(ReportExportHandler.START_DATE, null);
		returnMap.put(ReportExportHandler.END_DATE, null);
		returnMap.put(ReportExportHandler.ESTIMATED, null);
		returnMap.put(ReportExportHandler.COMPLETED, null);
		returnMap.put(ReportExportHandler.REMAINING, null);
		returnMap.put(ReportExportHandler.PERCENTAGE, null);
		returnMap.put(ReportExportHandler.RESOURCE, null);
		returnMap.put(ReportExportHandler.STATUS, null);
		returnMap.put(ReportExportHandler.REMARKS, null);
		returnMap.put(ReportExportHandler.IS_ROW_PROC, null);
		return returnMap;
	}

}


