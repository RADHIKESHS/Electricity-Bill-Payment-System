package com.masaischool.cw_project.Entitys;

import java.time.LocalDateTime;

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
    private LocalDateTime complaintDate;
    
    @Column(name = "complaint_description")
    private String complaintDescription;
    
    @Column(name = "complaint_types")
    private String complaintTypes;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ComplaintStatus status;

    @Column(name = "is_deleted")
    private boolean isDeleted;
    
    public Complaint() {
    	super();
    	// TODO Auto-generated constructor stub
    }
      
    public Complaint(Consumer consumer,String complaintDescription,String complaintTypes,ComplaintStatus status, boolean isDeleted) {
		super();
		this.consumer = consumer;
		this.complaintDate = LocalDateTime.now();
		this.complaintDescription = complaintDescription;
		this.complaintTypes = complaintTypes;
		this.status = status;
		this.isDeleted = isDeleted;
	}
    
    public Complaint(Consumer consumer,String complaintDescription,String complaintTypes ) {
		super();
		this.consumer = consumer;
		this.complaintDate = LocalDateTime.now();
		this.complaintDescription = complaintDescription;
		this.status = ComplaintStatus.PENDING;
		this.complaintTypes = complaintTypes;
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



	public String getComplaintTypes() {
		return complaintTypes;
	}

	public void setComplaintTypes(String complaintTypes) {
		this.complaintTypes = complaintTypes;
	}

	public void setComplaintDate(LocalDateTime complaintDate) {
		this.complaintDate = complaintDate;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", consumer=" + consumer + ", complaintDate=" + complaintDate
				+ ", complaintDescription=" + complaintDescription + ", complaintTypes=" + complaintTypes + ", status="
				+ status + ", isDeleted=" + isDeleted + "]";
	}

	public enum ComplaintStatus {
    	PENDING,
    	IN_PROGRESS,
    	RESOLVED
    	}
    
}
