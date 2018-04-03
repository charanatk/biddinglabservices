package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PaymentBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	@Column(nullable=false,length=15)
	private String customerName;
	@Column
	@JsonIgnore
	private String password;
	@Column(unique=true,nullable=false)
	private String status;
	@Column
	private String respMsg;
	@Column
	private String createdDate;
	@Column
	private String modifiedDate;
	@Column
	private Boolean active;
	@Column
	private String mobile;
	@Column
	private Boolean verifyMobile;

}
