package com.masaischool.cw_project.Entitys;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "complaint")
public class Complaint {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", referencedColumnName = "consumer_id")
    private Consumer consumer;
    
    @Column(name = "complaint_date")
    private Date complaintDate;
    
    @Column(name = "complaint_description")
    private String complaintDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ComplaintStatus status;

    @Column(name = "is_deleted")
    private boolean isDeleted;
    
    public Complaint() {
    	super();
    	// TODO Auto-generated constructor stub
    }
      
    public Complaint(Consumer consumer, Date complaintDate, String complaintDescription,
			ComplaintStatus status, boolean isDeleted) {
		super();
		this.consumer = consumer;
		this.complaintDate = complaintDate;
		this.complaintDescription = complaintDescription;
		this.status = status;
		this.isDeleted = isDeleted;
	}

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getComplaintDescription() {
		return complaintDescription;
	}

	public void setComplaintDescription(String complaintDescription) {
		this.complaintDescription = complaintDescription;
	}

	public ComplaintStatus getStatus() {
		return status;
	}

	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", consumer=" + consumer + ", complaintDate=" + complaintDate
				+ ", complaintDescription=" + complaintDescription + ", status=" + status + ", isDeleted=" + isDeleted
				+ "]";
	}

	enum ComplaintStatus {
    	PENDING,
    	IN_PROGRESS,
    	RESOLVED
    	}
    
}
