package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CustomerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Column(nullable=false,length=15)
	@NotNull(message="customerName should not be Null.")
	@NotEmpty(message="customerName should not be empty.")
	private String customerName;
	@Column
	@Size(max=10,min=6,message="Password size should be between 6-10 character")
	@NotNull(message="Password should be not Null.")
	@NotEmpty(message="Password should be empty.")
	private String password;
	@Column(unique=true,nullable=false)
	@Email(message="Enter valid mail id")
	@NotNull(message="customer email should not be Null.")
	@NotEmpty(message="customer email should not be empty.")
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	             message="Enter valid mail id")
	private String customerEmail;
	@Column(unique=true,nullable=false)
	@Email(message="Enter valid mail id")
	@NotNull(message="customer email should not be Null.")
	@NotEmpty(message="customer email should not be empty.")
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	             message="Enter valid mail id")
	private Boolean verifyEmail;
	@Column
	@NotNull(message="modified Date should not be Null.")
	@NotEmpty(message="modified Date should not be empty.")
	private String createdDate;
	@Column
	@NotNull(message="created Date should not be Null.")
	@NotEmpty(message="created Date should not be empty.")
	private String modifiedDate;
	@Column
	@NotNull(message="active value should not be Null.")
	@NotEmpty(message="active value should not be empty.")
	private Boolean active;
	@Column
//	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
//     message="{invalid.phonenumber}")
	@NotNull(message="mobile number should not be Null.")
	@NotEmpty(message="mobile number should not be empty.")
	private String mobile;
	@Column
	private Boolean verifyMobile;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Boolean getVerifyEmail() {
		return verifyEmail;
	}
	public void setVerifyEmail(Boolean verifyEmail) {
		this.verifyEmail = verifyEmail;
	}
	public Boolean getVerifyMobile() {
		return verifyMobile;
	}
	public void setVerifyMobile(Boolean verifyMobile) {
		this.verifyMobile = verifyMobile;
	}
	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", customerName="
				+ customerName + ", password=" + password + ", customerEmail="
				+ customerEmail + ", verifyEmail=" + verifyEmail
				+ ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", active=" + active + ", mobile=" + mobile
				+ ", verifyMobile=" + verifyMobile + "]";
	}

}
