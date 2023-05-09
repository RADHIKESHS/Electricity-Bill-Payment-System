package com.masaischool.cw_project.dao;

import com.masaischool.cw_project.Entitys.Transaction;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
import jakarta.persistence.EntityManager;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public Transaction addTransaction(Transaction transaction) throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(transaction);
            entityManager.getTransaction().commit();
            return transaction;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new SomethingWentWrongException("Unable to add transaction",e);
        } finally {
            entityManager.close();
        }
    }

}

