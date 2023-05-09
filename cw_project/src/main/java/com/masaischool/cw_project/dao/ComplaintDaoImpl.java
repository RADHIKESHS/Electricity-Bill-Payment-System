package com.masaischool.cw_project.dao;

import java.util.List;

import com.masaischool.cw_project.Entitys.Complaint;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ComplaintDaoImpl implements ComplaintDao {

    @Override
    public void saveComplaint(Complaint complaint) throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(complaint);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new SomethingWentWrongException("Failed to save complaint", e);
        } finally {
            entityManager.close();
        }
    }


    @SuppressWarnings("unchecked")
	@Override
    public List<Complaint> getComplaintsByConsumer(long consumerId) throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
        	Query query = entityManager.createQuery("SELECT c FROM Complaint c WHERE c.consumer.id = :consumerId AND c.status = :status");
        	query.setParameter("consumerId", consumerId);
            query.setParameter("status", Complaint.ComplaintStatus.PENDING);
            List<Complaint> complaints = query.getResultList();
            return complaints;
        } catch (Exception e) {
            throw new SomethingWentWrongException("Failed to get complaints by consumer id", e);
        } finally {
            entityManager.close();
        }
    }


    
    @Override
    public List<Complaint> getAllComplaints() throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            TypedQuery<Complaint> query = entityManager.createQuery("SELECT c FROM Complaint c JOIN FETCH c.consumer", Complaint.class);
            List<Complaint> complaints = query.getResultList();
            return complaints;
        } catch (Exception e) {
            throw new SomethingWentWrongException("Failed to get all complaints", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void updateComplaint(Complaint complaintToUpdate) throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(complaintToUpdate);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new SomethingWentWrongException("Failed to update complaint", e);
        } finally {
            entityManager.close();
        }
    }
}
