package com.masaischool.cw_project.dao;

import java.util.List;

import com.masaischool.cw_project.Entitys.Bill;
import com.masaischool.cw_project.Entitys.Bill.Status;
import com.masaischool.cw_project.Exceptions.BillNotFoundException;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class BillDaoImpl implements BillDao {
	
	@Override
	public Bill getBillsByConsumer(Long consumerId) throws SomethingWentWrongException {
	    EntityManager entityManager = null;
	    try {
	        entityManager = EMUtils.getAnEntityManager();
	        Query query = entityManager.createQuery("SELECT b FROM Bill b JOIN FETCH b.consumer c WHERE c.id = :consumerId", Bill.class);
	        query.setParameter("consumerId", consumerId);
	        Bill bill = (Bill) query.getSingleResult();
	        return bill;
	    } catch (NoResultException e) {
	        return null;
	    } catch (Exception e) {
	        throw new SomethingWentWrongException("There is no bill with this consumerID", e);
	    } finally {
	        if (entityManager != null) {
	            entityManager.close();
	        }
	    }
	}

	

    @Override
    public Bill getPendingBillsByConsumer(Long consumerId) throws SomethingWentWrongException, ClassNotFoundException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            Query query = entityManager.createQuery("SELECT b FROM Bill b WHERE b.consumer.id = :consumerId AND b.status != :status", Bill.class);
            query.setParameter("consumerId", consumerId);
            query.setParameter("status", Bill.Status.PAID);
            Bill bills = (Bill) query.getSingleResult();
            return bills;
        } catch (Exception e) {
            throw new SomethingWentWrongException("All bill are paid",e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Bill getBillById(long billId) throws SomethingWentWrongException, ClassNotFoundException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            Bill bill = entityManager.find(Bill.class, billId);
            return bill;
        } catch (Exception e) {
            throw new SomethingWentWrongException("There is no Bill with this Bill id",e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void updateBill(Bill bill) throws SomethingWentWrongException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(bill);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new SomethingWentWrongException("Something went wrong while updating bill",e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
    
    @Override
    public boolean updateBill2(Bill billToUpdate) throws SomethingWentWrongException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(billToUpdate);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new SomethingWentWrongException("Something went wrong while updating the bill", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Bill> getAllBills() throws SomethingWentWrongException, ClassNotFoundException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            Query query = entityManager.createQuery("SELECT b FROM Bill b JOIN FETCH b.consumer", Bill.class);
            List<Bill> bills = query.getResultList();
            return bills;
        } catch (Exception e) {
            throw new SomethingWentWrongException("Something went wrong while getting all bills", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    
	@SuppressWarnings("unchecked")
	@Override
    public List<Bill> getBillsByStatus(Status status) throws SomethingWentWrongException, ClassNotFoundException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            Query query = entityManager.createQuery("SELECT b FROM Bill b JOIN FETCH b.consumer", Bill.class);
            query.setParameter("status", status);
            List<Bill> bills = query.getResultList();
            return bills;
        } catch (Exception e) {
            throw new SomethingWentWrongException("Something went wrong while getting bills by status", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean createBill(Bill newBill) throws SomethingWentWrongException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(newBill);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new SomethingWentWrongException("Something went wrong while creating a new bill", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }



    @Override
    public boolean deleteBill(Long id) throws SomethingWentWrongException, BillNotFoundException {
        EntityManager entityManager = null;
        try {
            entityManager = EMUtils.getAnEntityManager();
            entityManager.getTransaction().begin();
            Bill bill = entityManager.find(Bill.class, id);
            if (bill == null) {
                throw new BillNotFoundException("Bill with id " + id + " not found");
            }
            // Set the foreign key to null
            Query query = entityManager.createQuery("UPDATE Transaction t SET t.bill = NULL WHERE t.bill = :bill");
            query.setParameter("bill", bill);
            query.executeUpdate();
            // Remove the bill
            entityManager.remove(bill);
            entityManager.getTransaction().commit();
            return true;
        } catch (BillNotFoundException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            throw new SomethingWentWrongException("Something went wrong while deleting the bill", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }




}

