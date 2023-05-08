package com.masaischool.cw_project.dao;

import java.util.List;

import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ConsumerDaoImpl implements ConsumerDao {

    @Override
    public Consumer addConsumer(Consumer consumer) throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(consumer);
            entityManager.getTransaction().commit();
            return consumer;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new SomethingWentWrongException("Failed to add consumer",e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Consumer getConsumerByUsernameAndPassword(String username, String password) throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM Consumer c WHERE c.username = :username AND c.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Consumer> consumers = query.getResultList();
            if (consumers.isEmpty()) {
                return null;
            } else {
                return consumers.get(0);
            }
        } catch (Exception e) {
            throw new SomethingWentWrongException("Failed to get consumer by username and password",e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Consumer getConsumerById(Long consumerId) throws SomethingWentWrongException, ClassNotFoundException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            Consumer consumer = entityManager.find(Consumer.class, consumerId);
            if (consumer == null) {
                throw new ClassNotFoundException("Consumer not found");
            }
            return consumer;
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new SomethingWentWrongException("Failed to get consumer by id",e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Consumer> getAllConsumers() throws SomethingWentWrongException {
        EntityManager entityManager = EMUtils.getAnEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM Consumer c");
            List<Consumer> consumers = query.getResultList();
            return consumers;
        } catch (Exception e) {
            throw new SomethingWentWrongException("Failed to get all consumers", e);
        } finally {
            entityManager.close();
        }
    }


}
