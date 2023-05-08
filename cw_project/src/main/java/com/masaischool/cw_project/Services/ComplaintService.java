package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface ComplaintService {
    List<Complaint> getComplaintsByConsumer(long complaintId) throws SomethingWentWrongException;
	Complaint createComplaint(Long id, String complaintDescription, String complaintType) throws Exception;
	List<Complaint> getAllComplaints() throws SomethingWentWrongException;
	boolean updateComplaint(Complaint complaintToUpdate) throws SomethingWentWrongException;
}

