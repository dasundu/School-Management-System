package com.assignmnet_Exceptions;

public class AssignmentOperationException extends Exception implements assignment_exception_handler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void logExceptionToConsole(String operation) {
		
		System.out.println("AssignmentOperationException : Failed operation - " + operation);
	}

}
