package com.equiniti.qa_report.form.model;

import org.springframework.ui.Model;

public abstract class AbstractModelAttribute {
	
	private Model model;
	
	private boolean isSuccess = true;
	
	private String resultMapping;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getResultMapping() {
		return resultMapping;
	}

	public void setResultMapping(String resultMapping) {
		this.resultMapping = resultMapping;
	}
	
}
