package com.equiniti.qa_report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ItemTable")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="qa_report")
public class ItemEntity implements Serializable{
	
	
	private static final long serialVersionUID = 6132396313576051532L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gKey", unique = true, nullable = false)
	private int gKey;

	@Column(name = "itemno", nullable = false)
	private int itemNo;
	
	@OneToOne
	@JoinColumn(name = "btpno")
	private BtpEntity btpNo;
	
	@Column(name = "itemdescription", nullable = false , length = 50)
	private String itemDescription;
	
	@Column(name = "itemcount", nullable = false )
	private int itemCount;
	
	@Column(name = "esteffort", nullable = false )
	private float estimatedEffort;
	
	@Column(name = "acteffort")
	private float actualEffort;
	
	@Column(name = "itemstatus", nullable = false , length = 20)
	private String itemStatus;
	
	@Column(name = "itemremarks" , length = 1000)
	private String itemRemarks;


	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public float getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(float estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

	public float getActualEffort() {
		return actualEffort;
	}

	public void setActualEffort(float actualEffort) {
		this.actualEffort = actualEffort;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getItemRemarks() {
		return itemRemarks;
	}

	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public BtpEntity getBtpNo() {
		return btpNo;
	}

	public void setBtpNo(BtpEntity btpNo) {
		this.btpNo = btpNo;
	}
	
}
