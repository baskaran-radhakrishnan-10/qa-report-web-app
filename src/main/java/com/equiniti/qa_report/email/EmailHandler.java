package com.equiniti.qa_report.email;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.equiniti.qa_report.util.ApplicationConstants;

@Service("emailService")
public class EmailHandler {

	private JavaMailSenderImpl mailSender;
	
	public EmailHandler(EmailConfigService mailConfigService){
		this.mailSender = mailConfigService.getMailSenderImplObj();
	}
	
	public void sendEmail(Map<String,Object> emailDetailsMap){
		
		String emailFrom=null != emailDetailsMap.get(ApplicationConstants.EMAIL_FROM) ? emailDetailsMap.get(ApplicationConstants.EMAIL_FROM).toString() : null;

		String emailSubject=null != emailDetailsMap.get(ApplicationConstants.EMAIL_SUBJECT) ? emailDetailsMap.get(ApplicationConstants.EMAIL_SUBJECT).toString() : null;

		String emailBodyContent=null != emailDetailsMap.get(ApplicationConstants.EMAIL_BODY) ? emailDetailsMap.get(ApplicationConstants.EMAIL_BODY).toString() : null;

		String[] emailTo=null != emailDetailsMap.get(ApplicationConstants.EMAIL_TO) ? (String[])emailDetailsMap.get(ApplicationConstants.EMAIL_TO) : null;

		String[] ccTo=null != emailDetailsMap.get(ApplicationConstants.EMAIL_CC) ? (String[])emailDetailsMap.get(ApplicationConstants.EMAIL_CC) : null;

		String[] bccTo=null != emailDetailsMap.get(ApplicationConstants.EMAIL_BCC) ? (String[])emailDetailsMap.get(ApplicationConstants.EMAIL_BCC) : null;

		byte[] attachmentContent=null != emailDetailsMap.get(ApplicationConstants.EMAIL_ATTACHMENT) ? (byte[]) emailDetailsMap.get(ApplicationConstants.EMAIL_ATTACHMENT) : null;

		String attachmentType=null != emailDetailsMap.get(ApplicationConstants.EMAIL_ATTACHMENT_TYPE) ? emailDetailsMap.get(ApplicationConstants.EMAIL_ATTACHMENT_TYPE).toString() : null;

		String attachmentFileName=null != emailDetailsMap.get(ApplicationConstants.EMAIL_ATTACHMENT_FILE_NAME) ? emailDetailsMap.get(ApplicationConstants.EMAIL_ATTACHMENT_FILE_NAME).toString() : null;
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);

				message.setTo(emailTo);

				message.setFrom(emailFrom);

				if(null != bccTo){
					message.setBcc(bccTo);
				}
				
				if(null != ccTo){
					message.setCc(ccTo);
				}

				if(null != emailSubject){
					message.setSubject(emailSubject);
				}

				if(null != emailBodyContent){
					message.setText(emailBodyContent, true);
				}

				if(null != attachmentContent){
					ByteArrayResource bar = new ByteArrayResource(attachmentContent);
					message.addAttachment(attachmentFileName,bar,attachmentType);
				}

			}

		};
		
		mailSender.send(preparator);

	}
	
	public void sendEmail(EmailBean emailBean){
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);

				message.setTo(emailBean.getEmailTo());

				message.setFrom(mailSender.getUsername());

				if(null != emailBean.getBccTo()){
					message.setBcc(emailBean.getBccTo());
				}
				
				if(null != emailBean.getCcTo()){
					message.setCc(emailBean.getCcTo());
				}

				if(null != emailBean.getSubject()){
					message.setSubject(emailBean.getSubject());
				}

				if(null != emailBean.getBodyContent()){
					message.setText(emailBean.getBodyContent(), true);
				}

				if(null != emailBean.getAttachmentContent()){
					ByteArrayResource bar = new ByteArrayResource(emailBean.getAttachmentContent());
					message.addAttachment(emailBean.getAttachmentFileName(),bar,emailBean.getAttachmentType());
				}

			}

		};

		mailSender.send(preparator);

	}
	
}
