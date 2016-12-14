package com.equiniti.qa_report.email;

import java.io.Serializable;

public class EmailBean implements Serializable{

	private static final long serialVersionUID = 270190442213149368L;
	
	private String emailFrom=null;
	
	private String[] emailTo=null;
	
	private String[] ccTo=null;
	
	private String[] bccTo=null;
	
	private String subject=null;
	
	private String bodyContent=null;
	
	private String attachmentType=null;
	
	private String attachmentFileName=null;
	
	private byte[] attachmentContent=null;

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String[] getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String[] emailTo) {
		this.emailTo = emailTo;
	}

	public String[] getCcTo() {
		return ccTo;
	}

	public void setCcTo(String[] ccTo) {
		this.ccTo = ccTo;
	}

	public String[] getBccTo() {
		return bccTo;
	}

	public void setBccTo(String[] bccTo) {
		this.bccTo = bccTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public byte[] getAttachmentContent() {
		return attachmentContent;
	}

	public void setAttachmentContent(byte[] attachmentContent) {
		this.attachmentContent = attachmentContent;
	}
 
}
