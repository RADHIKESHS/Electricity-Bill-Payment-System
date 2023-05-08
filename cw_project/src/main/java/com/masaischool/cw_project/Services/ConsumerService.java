package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;

public interface ConsumerService {
    Consumer createConsumer(String firstName, String lastName, String username, String password, String address, String mobileNumber, String email) throws Exception;
    Consumer login(String username, String password) throws Exception;
	List<Consumer> getAllConsumers() throws SomethingWentWrongException;
	Consumer getConsumerById(long consumerId) throws ClassNotFoundException, SomethingWentWrongException;
}

