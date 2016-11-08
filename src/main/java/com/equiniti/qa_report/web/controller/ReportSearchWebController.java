package com.equiniti.qa_report.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.equiniti.qa_report.controller.ReportSearchController;
import com.equiniti.qa_report.exception.api.exception.ControllerException;
import com.equiniti.qa_report.exception.api.exception.UIException;
import com.equiniti.qa_report.exception.api.faultcode.CommonFaultCode;
import com.equiniti.qa_report.util.ApplicationConstants;

@Controller
@RequestMapping(value="report_search")
public class ReportSearchWebController {
	
	private static final Logger LOG = Logger.getLogger(ReportSearchWebController.class);
	
	@Autowired
	private ReportSearchController reportSearchController;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/btp_report_search" , method = RequestMethod.GET)
	public String showBtpReportSearchPage(Model model) throws UIException{
		return "btp_report_search";
	}
	
	@RequestMapping(value = "/export_document/{fileId}" , method = RequestMethod.GET)
	public void downloadReportDocument(HttpServletRequest request,HttpServletResponse response,@PathVariable("fileId") String fileId) throws UIException{
		try {
			reportSearchController.downloadReportDocument(fileId, request.getSession().getAttribute(ApplicationConstants.USER_ID).toString());
		} catch (IOException e) {
			throw new UIException(CommonFaultCode.UNKNOWN_ERROR, e);
		}catch (ControllerException e){
			throw new UIException(e.getFaultCode(), e);
		}
	}
	
	@RequestMapping(value = "/exportBTPRow", method = RequestMethod.POST)
	@ResponseBody
	public void exportSelectedBTPRow(@RequestBody Map<String,Object> inputData) throws UIException{
		try {
			reportSearchController.exportBTPRowData(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
	}
	
	@RequestMapping(value = "/buildBtpMonthlyReport", method = RequestMethod.POST)
	@ResponseBody
	public void buildBtpMonthlyReport(@RequestBody Map<String,Object> inputData) throws UIException{
		try {
			reportSearchController.buildBtpMonthlyReport(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
	}
	
	@RequestMapping(value = "/dsr_report_search" , method = RequestMethod.GET)
	public String showDSRReportSearchPage(Model model) throws UIException{
		return "dsr_report_search";
	}
	
	@RequestMapping(value = "/user_report_search" , method = RequestMethod.GET)
	public String showUserReportSearchPage(Model model) throws UIException{
		return "user_report_search";
	}
	
	@RequestMapping(value = "/filter_user_report", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> filterUserReportData(@RequestBody Map<String,Object> inputData) throws UIException{
		LOG.debug("START filterData() Method!!!");
		Map<String,Object> returnObj=new HashMap<>();
		try {
			returnObj = reportSearchController.filterUserReportData(inputData);
		} catch (ControllerException e) {
			throw new UIException(e.getFaultCode(), e);
		}
		LOG.debug("END filterData() Method!!!");
		return returnObj;
	}
	
	@RequestMapping(value = "/leave_report_search" , method = RequestMethod.GET)
	public String showLeaveReportSearchPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Report Search/Leave Report Search");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/permission_report_search" , method = RequestMethod.GET)
	public String showPermissionReportSearchPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Report Search/Permission Report Search");
		return "under_construction_page";
	}
	
	@RequestMapping(value = "/leave_plan_report_search" , method = RequestMethod.GET)
	public String showLeavePlanReportSearchPage(Model model) throws UIException{
		session.setAttribute(ApplicationConstants.CURRENT_ACTION_PATH, "Report Search/Leave Plan Report Search");
		return "under_construction_page";
	}
	
}
