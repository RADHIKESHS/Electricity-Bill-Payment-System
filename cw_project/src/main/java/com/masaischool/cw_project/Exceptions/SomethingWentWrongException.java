package com.masaischool.cw_project.Exceptions;


public class SomethingWentWrongException extends Exception {
    public SomethingWentWrongException(String message, Throwable cause) {
        super(message, cause);
    }

	public SomethingWentWrongException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
}
