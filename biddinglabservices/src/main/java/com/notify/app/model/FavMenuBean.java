package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class FavMenuBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4616439085437478340L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer favId;
	@Column
	private Integer itemId;
	@Column
	private Integer CustomerID;
	@Column
	@NotNull(message="Created Date should not be Null.")
	@NotEmpty(message="Created Date should not be empty.")
	private String createdDate;
	@Column
	@NotNull(message="Modified Date should not be Null.")
	@NotEmpty(message="Modified Date should not be empty.")
	private String modifiedDate;
	@Column
	private Integer restaurantId;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public Integer getFavId() {
		return favId;
	}
	public void setFavId(Integer favId) {
		this.favId = favId;
	}
	public Integer getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Integer customerID) {
		CustomerID = customerID;
	}
}
