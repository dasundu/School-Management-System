package com.teacher_Exceptions;

public class TeacherOperationException extends Exception implements teacher_exception_handler
{

	private static final long serialVersionUID = 1L;

	@Override
	public void logTeacherExceptionToConsole(String teacherOperation) {
		System.out.println("TeacherOperationException : Failed - " + teacherOperation);
		
	}
}
