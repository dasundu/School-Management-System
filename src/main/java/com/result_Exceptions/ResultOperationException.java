package com.result_Exceptions;

public class ResultOperationException extends Exception implements results_exception_handler{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void logResultException(String failedOperation) {
		System.out.println("ResultOperationException : Failed operation in Results - " + failedOperation);
	}

}
