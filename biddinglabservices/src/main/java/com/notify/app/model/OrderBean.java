package com.notify.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class OrderBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@Column
	private Integer tokenId;
	@Column
	private Integer customerId;
	@Column
	private Integer restaurantId;
	@Column
	private String orderDetails;
	@Column
	private String createdDate;
	@Column
	private String modifiedDate;
	@Column
	private Boolean activeOrder;
	@Column
	private String orderDate;
	@Column
	private Boolean orderAccept;
	@Column
	private Boolean takeWay;
	@Column
	private String specialComment;
	//specialComment like we need spicy
	//@ManyToOne
    //@JoinColumn(name="notification_id", nullable=false)
	//private NotificationBean notificationBean;
	
	public String getSpecialComment() {
		return specialComment;
	}
	public void setSpecialComment(String specialComment) {
		this.specialComment = specialComment;
	}
	public Boolean getTakeWay() {
		return takeWay;
	}
	public void setTakeWay(Boolean takeWay) {
		this.takeWay = takeWay;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getTokenId() {
		return tokenId;
	}
	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
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
	public Boolean getActiveOrder() {
		return activeOrder;
	}
	public void setActiveOrder(Boolean activeOrder) {
		this.activeOrder = activeOrder;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public Boolean getOrderAccept() {
		return orderAccept;
	}
	public void setOrderAccept(Boolean orderAccept) {
		this.orderAccept = orderAccept;
	}

}
