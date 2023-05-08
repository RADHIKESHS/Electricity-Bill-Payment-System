package com.masaischool.cw_project.Services;

import java.util.List;

import com.masaischool.cw_project.Entitys.Consumer;
import com.masaischool.cw_project.Exceptions.SomethingWentWrongException;
import com.masaischool.cw_project.dao.ConsumerDao;
import com.masaischool.cw_project.dao.ConsumerDaoImpl;

public class ConsumerServiceImpl implements ConsumerService {

    private ConsumerDao consumerDao;

    public ConsumerServiceImpl() {
        consumerDao = new ConsumerDaoImpl();
    }

    @Override
    public Consumer createConsumer(String firstName, String lastName, String username, String password, String address, String mobileNumber, String email) throws Exception {
        Consumer consumer = new Consumer(firstName, lastName, username, password, address, mobileNumber, email);
        return consumerDao.addConsumer(consumer);
    }

    @Override
    public Consumer login(String username, String password) throws Exception {
        return consumerDao.getConsumerByUsernameAndPassword(username, password);
    }

    @Override
    public List<Consumer> getAllConsumers() throws SomethingWentWrongException {
        return consumerDao.getAllConsumers();
    }

    @Override
    public Consumer getConsumerById(long consumerId) throws ClassNotFoundException, SomethingWentWrongException {
        return consumerDao.getConsumerById(consumerId);
    }


}
