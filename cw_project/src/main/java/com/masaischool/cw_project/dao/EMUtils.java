package com.masaischool.cw_project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {
	static EntityManagerFactory emf;
	static {
		emf= Persistence.createEntityManagerFactory("Electricity_Bill_Payment_System");
	}
	
	static EntityManager getAnEntityManager() {
		return emf.createEntityManager();
	}

}
