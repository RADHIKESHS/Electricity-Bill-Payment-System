package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
import com.masaischool.cw_project.dao.ComplaintDao;
import com.masaischool.cw_project.dao.ComplaintDaoImpl;
import com.masaischool.cw_project.dao.ConsumerDao;
import com.masaischool.cw_project.dao.ConsumerDaoImpl;

public class ComplaintServiceImpl implements ComplaintService {
    private ComplaintDao complaintDao;
    private ConsumerDao consumerDao;

    public ComplaintServiceImpl() {
        this.complaintDao = new ComplaintDaoImpl();
        this.consumerDao = new ConsumerDaoImpl();
    }

    @Override
    public Complaint createComplaint(Long consumerId, String description, String complaintType) throws Exception {
        Consumer consumer = consumerDao.getConsumerById(consumerId);
        Complaint complaint = new Complaint(consumer, description, complaintType);
        complaintDao.saveComplaint(complaint);
        return complaint;
    }

    @Override
    public List<Complaint> getComplaintsByConsumer(long consumerId) throws SomethingWentWrongException {
        return complaintDao.getComplaintsByConsumer(consumerId);
    }

    @Override
    public List<Complaint> getAllComplaints() throws SomethingWentWrongException {
    	return complaintDao.getAllComplaints();
    }

    @Override
    public boolean updateComplaint(Complaint complaintToUpdate) throws SomethingWentWrongException {
    	complaintDao.updateComplaint(complaintToUpdate);
    	return true;
    }

}


