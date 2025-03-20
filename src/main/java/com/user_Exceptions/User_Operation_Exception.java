package com.user_Exceptions;

public class User_Operation_Exception extends Exception implements user_exception_handler{

	private static final long serialVersionUID = 1L;

	@Override
	public void logExceptionToConsole(String message) {
		System.out.println("User_Operation_Exception : " + message);
	}



}
