package com.masaischool.cw_project.Exceptions;

import jakarta.persistence.NoResultException;

@SuppressWarnings("serial")
public class BillNotFoundException extends Exception {


	public BillNotFoundException(String string, NoResultException e) {
		// TODO Auto-generated constructor stub
		super(string,e);
	}

	public BillNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

}
