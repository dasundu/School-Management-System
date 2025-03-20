package com.user_Exceptions;

public class InvalidRoleException extends Exception implements user_exception_handler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void logExceptionToConsole(String role) {
		
		System.out.println("Invalid Role :" + role);
	}



}
