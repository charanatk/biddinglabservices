package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RestaurantBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	@Column
	private String restaurantName;
	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String latitude;
	@Column
	private String longitude;
	@Column
	private String zipcode;
	@Column
	private String createdDate;
	@Column
	private String modifiedDate;
	@Column
	private Boolean active;
	@Column
	private String mobileNumber;
	@Column
	private String phone;
	@Column
	private String url;
	@Column
	private String email;
	@Column
	private String workingHours;
	@Column
	private String rating;
	@Column
	private String facebookURl;	
	@Column
	private Boolean verifyEmail;
	@Column
	private Boolean verifyMobileNumber;
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFacebookURl() {
		return facebookURl;
	}
	public void setFacebookURl(String facebookURl) {
		this.facebookURl = facebookURl;
	}
	public Boolean getVerifyEmail() {
		return verifyEmail;
	}
	public void setVerifyEmail(Boolean verifyEmail) {
		this.verifyEmail = verifyEmail;
	}
	public Boolean getVerifyMobileNumber() {
		return verifyMobileNumber;
	}
	public void setVerifyMobileNumber(Boolean verifyMobileNumber) {
		this.verifyMobileNumber = verifyMobileNumber;
	}

}
