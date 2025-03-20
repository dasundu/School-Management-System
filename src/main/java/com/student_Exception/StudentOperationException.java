package com.student_Exception;

public class StudentOperationException extends Exception implements student_exception_handler
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void logStudentExceptionToConsole(String studentOperation) {
		System.out.println("StudentOperationException : Failed - " +  studentOperation);
		
	}
}
