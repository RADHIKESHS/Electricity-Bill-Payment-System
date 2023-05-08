package com.masaischool.cw_project.Services;


import com.masaischool.cw_project.dao.ConsumerDao;
import com.masaischool.cw_project.dao.ConsumerDaoImpl;
//import com.masaischool.cw_project.model.Consumer;

public class ConsumerServiceImpl implements ConsumerService {

    private ConsumerDao consumerDao;

    public ConsumerServiceImpl() {
        consumerDao = new ConsumerDaoImpl();
    }

    @Override
    public Consumer createConsumer(String firstName, String lastName, String username, String password, String address, String mobileNumber, String email) {
        Consumer consumer = new Consumer(firstName, lastName, username, password, address, mobileNumber, email);
        return consumerDao.addConsumer(consumer);
    }

    @Override
    public Consumer login(String username, String password) {
        return consumerDao.getConsumerByUsernameAndPassword(username, password);
    }
}

