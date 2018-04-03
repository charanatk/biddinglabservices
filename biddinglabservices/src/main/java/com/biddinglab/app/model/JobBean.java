package com.biddinglab.app.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class JobBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer jobId;
	@Column(nullable = false, length = 15)
	@NotNull(message = "jobName should not be Null.")
	@NotEmpty(message = "jobName should not be empty.")
	private String jobTitle;
	@Column(nullable = false, length = 15)
	private String technologyName;
	@Column
	@NotNull(message = "modified Date should not be Null.")
	@NotEmpty(message = "modified Date should not be empty.")
	private String createdDate;
	@Column
	@NotNull(message = "created Date should not be Null.")
	@NotEmpty(message = "created Date should not be empty.")
	private String modifiedDate;
	@Column
	@NotNull(message = "active value should not be Null.")
	@NotEmpty(message = "active value should not be empty.")
	private Boolean active;
	// @Column
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	// message="{invalid.phonenumber}")

	@Column
	private String description;

	// Valid values: hourly, fixed-price
	@Column
	private String visibility;

	// Valid values: public, private, biddinglab, invite-only
	@Column
	private String jobType;
	@Column
	private int postedBy;
	@Column
	private String budget;
	@Column
	private String skills;
	@Column
	private String startDate;
	@Column
	private String endDate;
	@Column
	private int bidderId;
	@Column
	private int awardedTo;
	@Column
	private String[] technologies = null;
	@Column
	private String filePath;
	

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BidderBean> bidders;
    

	public Set<BidderBean> getBidders() {
		return bidders;
	}

	public void setBidders(Set<BidderBean> bidders) {
		this.bidders = bidders;
	}

	public Integer getJobId() {
		return jobId;
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

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getBidderId() {
		return bidderId;
	}

	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}

	public int getAwardedTo() {
		return awardedTo;
	}

	public void setAwardedTo(int awardedTo) {
		this.awardedTo = awardedTo;
	}

	public String[] getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String[] technologies) {
		this.technologies = technologies;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

}
