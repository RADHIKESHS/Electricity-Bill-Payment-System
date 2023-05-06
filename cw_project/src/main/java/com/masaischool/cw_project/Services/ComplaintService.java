package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Complaint;

public interface ComplaintService {
//    Complaint createComplaint(int consumerId, String description, ComplaintType type);
    
    
    List<Complaint> getComplaintsByConsumer(int consumerId);
}

