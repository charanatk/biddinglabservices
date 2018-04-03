/**
 * 
 */
package com.biddinglab.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author ck00462842
 *
 */
@Entity
public class BidderBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bidderId;
	@Column
	private int bidderStatus;
	@Column
	private String amount;
	@Column
	private String deliveryDays;
	@Column
	private boolean notifyByMail;
	@Column
	private String biddingMessage;
	@ManyToOne(fetch = FetchType.EAGER)
    private JobBean job;
  
	public JobBean getJob() {
		return job;
	}
	public void setJob(JobBean job) {
		this.job = job;
	}
	public int getBidderId() {
		return bidderId;
	}
	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}
	public int getBidderStatus() {
		return bidderStatus;
	}
	public void setBidderStatus(int bidderStatus) {
		this.bidderStatus = bidderStatus;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDeliveryDays() {
		return deliveryDays;
	}
	public void setDeliveryDays(String deliveryDays) {
		this.deliveryDays = deliveryDays;
	}
	public boolean isNotifyByMail() {
		return notifyByMail;
	}
	public void setNotifyByMail(boolean notifyByMail) {
		this.notifyByMail = notifyByMail;
	}
	public String getBiddingMessage() {
		return biddingMessage;
	}
	public void setBiddingMessage(String biddingMessage) {
		this.biddingMessage = biddingMessage;
	}

}
