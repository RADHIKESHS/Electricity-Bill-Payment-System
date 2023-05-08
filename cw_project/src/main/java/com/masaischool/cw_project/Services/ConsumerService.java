package com.masaischool.cw_project.Services;

import com.masaischool.cw_project.Entitys.Consumer;

public interface ConsumerService {
    Consumer createConsumer(String firstName, String lastName, String username, String password, String address, String mobileNumber, String email);
    Consumer login(String username, String password);
}

