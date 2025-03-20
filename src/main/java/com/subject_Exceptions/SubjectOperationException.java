package com.subject_Exceptions;

public class SubjectOperationException extends Exception implements subject_exception_handler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void logSubjectExceptionToConsole(String FailedSubjectOperation) {
		System.out.println("SubjectOperationException : Failed - " + FailedSubjectOperation);
		
	}

}
