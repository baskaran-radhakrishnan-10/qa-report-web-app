package com.equiniti.qa_report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_resources")
public class Resources extends GeneralEntity{

	private static final long serialVersionUID = 3513713317165388640L;

	@Column(name = "resource_url",unique = true, nullable = false)
	private String resourceUrl;
	
	@Column(name = "resource_module", nullable = false)
	private String resourceModule;
	
	@Column(name = "resource_name", nullable = false)
	private String resourceName;
	
	@Column(name = "resource_module_icon", nullable = false)
	private String resourceModuleIcon;
	
	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public String getResourceModule() {
		return resourceModule;
	}

	public void setResourceModule(String resourceModule) {
		this.resourceModule = resourceModule;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceModuleIcon() {
		return resourceModuleIcon;
	}

	public void setResourceModuleIcon(String resourceModuleIcon) {
		this.resourceModuleIcon = resourceModuleIcon;
	}

	@Transient
	public Long getALogId() {
		return null;
	}

	@Transient
	public String getLogDeatil() {
		return null;
	}


}
