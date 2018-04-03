package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class FoodCategorieBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4448283432052798116L;
	/**
	 * 
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categorieId;
	@Column
	@NotNull(message="\"Error\":[{ \"errorId\": \"1001\", \"error\": \"categorieName is not Null.\" }]")
	@NotEmpty(message="\"Error\":[{ \"errorId\": \"1002\", \"error\": \"categorieName is Requried.\" }]")
	@Size(min=3)
	private String categorieName;
	@Column
	private String createdDate;
	@Column
	private String modifiedDate;
	@Column
	@NotNull(message="\"Error\":[{ \"errorId\": \"1001\", \"error\": \"restaurantId is not Null.\" }]")
	@NotEmpty(message="\"Error\":[{ \"errorId\": \"1002\", \"error\": \"restaurantId is Requried.\" }]")
	private Integer restaurantId;
	
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
	public Integer getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(Integer categorieId) {
		this.categorieId = categorieId;
	}
	public String getCategorieName() {
		return categorieName;
	}
	public void setCategorieName(String categorieName) {
		this.categorieName = categorieName;
	}
	
}
