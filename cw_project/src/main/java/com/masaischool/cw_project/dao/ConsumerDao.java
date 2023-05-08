package com.masaischool.cw_project.dao;

import java.util.List;

import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;


public interface ConsumerDao {
    public Consumer addConsumer(Consumer consumer) throws SomethingWentWrongException;
    public Consumer getConsumerByUsernameAndPassword(String username, String password) throws SomethingWentWrongException;
	public Consumer getConsumerById(Long consumerId) throws SomethingWentWrongException, ClassNotFoundException;
	public List<Consumer> getAllConsumers() throws SomethingWentWrongException;
}

