package com.equiniti.qa_report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jcs.access.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equiniti.qa_report.cache.CacheInstance;
import com.equiniti.qa_report.enums.ExportFileEnum;
import com.equiniti.qa_report.exception.api.exception.APIException;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.service.api.ReportSearchService;
import com.equiniti.qa_report.util.ApplicationConstants;

@Component("reportSearchController")
public class ReportSearchController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ReportSearchService reportSearchService;
	
	private CacheInstance CACHE_INS = null;
	
	public ReportSearchController() throws CacheException{
		this.CACHE_INS = CacheInstance.getInstance();
	}

	private static final int BUFFER_SIZE = 4096;

	public void downloadReportDocument(String fileId,String userId) throws IOException, ControllerException{
		FileInputStream inputStream = null;
		OutputStream outStream = null;
		int bytesRead = -1;
		byte[] buffer = new byte[BUFFER_SIZE];
		String headerKey = "Content-Disposition";
		try{
			ServletContext context = request.getServletContext();
			String downloadFilePath = ApplicationConstants.APP_CONFIG_FOLDER_PATH+File.separator+userId+File.separator+ExportFileEnum.valueOf(fileId);
			File downloadFile = new File(downloadFilePath);
			inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(downloadFilePath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			outStream = response.getOutputStream();
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
		}catch(IOException e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(null != outStream){
				outStream.close();
			}
		}
	}
	
	public void exportBTPRowData(Map<String,Object> inputData) throws ControllerException{
		try {
			reportSearchService.buildSelectedBTPReport(inputData);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		}catch(Exception e){
			 throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
	}
	
	public  Map<String,Object> buildBtpMonthlyReport(Map<String,Object> inputData) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			boolean isEmptyResult=reportSearchService.buildBTPMonthlyReport(inputData);
			returnObjMap.put(ApplicationConstants.STATUS, !isEmptyResult ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		}catch(Exception e){
			 throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
	public void downloadDSRDailyReport() throws ControllerException, IOException, CacheException{
		downloadGeneratedReport((String)request.getSession().getAttribute(ApplicationConstants.USER_ID), ExportFileEnum.DSR_DAY_REPORT.name(), ApplicationConstants.DSR_DAILY_REPORT_CONSTRUCTED);
	}
	
	public void downloadBTPMonthlyReport() throws ControllerException, IOException, CacheException{
		downloadGeneratedReport((String)request.getSession().getAttribute(ApplicationConstants.USER_ID), ExportFileEnum.BTP_MONTHLY.name(), ApplicationConstants.BTP_MONTHLY_REPORT_CONSTRUCTED);
	}
	
	private void downloadGeneratedReport(String userId,String enumName,String cacheKey)throws ControllerException, IOException, CacheException{
		boolean isReportConstructed = isReportConstructed(userId,cacheKey);
		if(isReportConstructed){
			downloadReport(userId, enumName, cacheKey);
		}else{
			boolean isReady = false ;
			while(!isReady){
				if(isReportConstructed((String)request.getSession().getAttribute(ApplicationConstants.USER_ID),cacheKey)){
					downloadReport(userId, enumName, cacheKey);
					isReady = true;
				}
			}
		}
	}
	
	private boolean isReportConstructed(String userId,String cacheKey){
		boolean isReportConstructed = (null == CACHE_INS.getItemFromCache(cacheKey, (String)request.getSession().getAttribute(ApplicationConstants.USER_ID))) ? false : true;
		return isReportConstructed;
	}
	
	private void downloadReport(String userId,String enumName,String cacheKey) throws ControllerException, IOException, CacheException{
		downloadReportDocument(enumName, userId);
		CACHE_INS.removeItemFromCache(cacheKey, userId);
	}
	
	public Map<String,Object> filterUserReportData(Map<String,Object> paramMap) throws ControllerException{
		Map<String,Object> returnObjMap=new HashMap<>();
		try {
			Object returnObj=reportSearchService.buildUserSummaryReport(paramMap);
			if(null != returnObj){
				returnObjMap.put(ApplicationConstants.STATUS, returnObj != null ? ApplicationConstants.SUCCESS : ApplicationConstants.ERROR);
				returnObjMap.put(ApplicationConstants.SERVER_DATA, returnObj);
			}
		} catch (APIException e) {
			throw new ControllerException(e.getFaultCode(), e);
		} catch(Exception e){
			throw new ControllerException(CommonFaultCode.UNKNOWN_ERROR, e);
		}
		return returnObjMap;
	}
	
}
