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
				String userId = (String) objectMessage.getObjectProperty(ApplicationConstants.USER_ID);
				String reportType=(String) objectMessage.getObjectProperty(ApplicationConstants.REPORT_TYPE);
				exportObject.put(ApplicationConstants.REPORT_TYPE, reportType);
				exportObject.put(ApplicationConstants.USER_ID, userId);
				if(ApplicationConstants.DSR_SUMMARY_REPORT.intern() == reportType.intern()){
					exportObject.put(ApplicationConstants.REPORT_DATA, (Map<Integer,List<DSREntity>>) objectMessage.getObjectProperty(ApplicationConstants.REPORT_DATA));
					reportExportHandler.exportDSRReport(exportObject);
				}else if(ApplicationConstants.BTP_SUMMARY_REPORT.intern() == reportType.intern()){
					exportObject.put(ApplicationConstants.REPORT_DATA, (List<Map<String, Object>>) objectMessage.getObjectProperty(ApplicationConstants.REPORT_DATA));
					reportExportHandler.exportBTPReport(exportObject);
					if(ApplicationConstants.SELECTED_BTP_REPORT.intern() != reportType.intern()){
						exportObject.put(ApplicationConstants.REPORT_TYPE, ApplicationConstants.BTP_WEEKLY_REPORT);
						reportExportHandler.exportBTPReport(exportObject);
					}
				}else if(ApplicationConstants.USER_SUMMARY_REPORT.intern().intern() == reportType.intern()){
					exportObject.put(ApplicationConstants.REPORT_DATA, (List<Map<String,Object>>) objectMessage.getObjectProperty(ApplicationConstants.REPORT_DATA));
					reportExportHandler.exportUserReport(exportObject);
				}else if(ApplicationConstants.SELECTED_BTP_REPORT.intern().intern() == reportType.intern()){
					exportObject.put(ApplicationConstants.REPORT_DATA, (List<Map<String,Object>>) objectMessage.getObjectProperty(ApplicationConstants.REPORT_DATA));
					reportExportHandler.exportBTPReport(exportObject);
				}else if(ApplicationConstants.BTP_MONTHLY_REPORT.intern().intern() == reportType.intern()){
					exportObject.put(ApplicationConstants.REPORT_DATA, (Map<String,List<Map<String, Object>>>) objectMessage.getObjectProperty(ApplicationConstants.REPORT_DATA));
					reportExportHandler.exportBTPMonthlyReport(exportObject);
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
