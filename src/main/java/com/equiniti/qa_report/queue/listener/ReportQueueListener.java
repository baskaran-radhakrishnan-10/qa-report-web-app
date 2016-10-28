package com.equiniti.qa_report.queue.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.equiniti.qa_report.entity.DSREntity;
import com.equiniti.qa_report.export.ReportExportHandler;
import com.equiniti.qa_report.util.ApplicationConstants;


public class ReportQueueListener implements MessageListener{
	
	@Autowired
	private ReportExportHandler reportExportHandler;

	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		if(null != message && message instanceof ObjectMessage){
			Map<String,Object> exportObject=new HashMap<>();
			ObjectMessage objectMessage=(ObjectMessage) message;
			try {
				String userId = (String) objectMessage.getObjectProperty("USER_ID");
				String reportType=(String) objectMessage.getObjectProperty("REPORT_TYPE");
				exportObject.put("REPORT_TYPE", reportType);
				exportObject.put("USER_ID", userId);
				if(ApplicationConstants.DSR_SUMMARY_REPORT.intern() == reportType.intern()){
					exportObject.put("REPORT_DATA", (Map<Integer,List<DSREntity>>) objectMessage.getObjectProperty("REPORT_DATA"));
					reportExportHandler.exportDSRReport(exportObject);
				}else if(ApplicationConstants.BTP_SUMMARY_REPORT.intern() == reportType.intern()){
					exportObject.put("REPORT_DATA", (List<Map<String, Object>>) objectMessage.getObjectProperty("REPORT_DATA"));
					reportExportHandler.exportBTPReport(exportObject);
					if(ApplicationConstants.SELECTED_BTP_REPORT.intern() != reportType.intern()){
						exportObject.put("REPORT_TYPE", "BTP_WEEKLY_REPORT");
						reportExportHandler.exportBTPReport(exportObject);
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
