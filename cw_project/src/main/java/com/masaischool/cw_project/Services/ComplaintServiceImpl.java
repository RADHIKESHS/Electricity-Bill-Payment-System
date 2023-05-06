package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Complaint;

public class ComplaintServiceImpl implements ComplaintService {
	private ComplaintDao complaintDao;

	public ComplaintServiceImpl() {
	    this.complaintDao = new ComplaintDaoImpl();
	}

	@Override
	public Complaint createComplaint(int consumerId, String description, ComplaintType type) {
	    // Implementation goes here
	    Complaint complaint = new Complaint(consumerId, description, type);
	    complaintDao.saveComplaint(complaint);
	    return complaint;
	}

	@Override
	public List<Complaint> getComplaintsByConsumer(int consumerId) {
	    // Implementation goes here
	    return complaintDao.getComplaintsByConsumer(consumerId);
	}

}

