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
@Table(name = "tbl_users")
public class GetUserDeatilsEntity extends GeneralEntity implements Serializable{

	private static final long serialVersionUID = -1968065884219305808L;

	@Column(name = "user_id", unique = true,nullable = false)
	private  String userId;

	@Column(name = "email_id", unique = true,nullable = false)
	private  String emailId;

	@OneToOne
	@JoinColumn(name = "role_id")
	private  Roles roleId;
	
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "is_active")
	private  boolean active;

	
	public String getUserId() {
		return userId;
	}
}
