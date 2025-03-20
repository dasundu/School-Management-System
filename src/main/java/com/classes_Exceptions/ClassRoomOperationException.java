package com.classes_Exceptions;

public class ClassRoomOperationException extends Exception implements classes_exception_handler{


	private static final long serialVersionUID = 1L;

	@Override
	public void logExceptionToConsole(String operation)
	{
		System.out.println("ClassRoomOperationException : Failed Classroom operation - " + operation);
	}

}
