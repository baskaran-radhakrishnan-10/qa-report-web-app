package com.equiniti.qa_report.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@Autowired
	private ReportSearchController reportSearchController;
	
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
	
}
