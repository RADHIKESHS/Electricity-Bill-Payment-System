package com.masaischool.cw_project.dao;

import java.util.List;
import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface ComplaintDao {

	void saveComplaint(Complaint complaint) throws SomethingWentWrongException;
	List<Complaint> getComplaintsByConsumer(long consumerId) throws SomethingWentWrongException;
	List<Complaint> getAllComplaints() throws SomethingWentWrongException;
	void updateComplaint(Complaint complaintToUpdate) throws SomethingWentWrongException;
}
