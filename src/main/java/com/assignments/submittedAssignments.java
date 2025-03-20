package com.assignments;

public class submittedAssignments extends assignment{
	
	private int submitid;
	private int studentid;
	private String studentName;
	private int assignmentid;
	private String assignmentName;
	private String UploadedFile;
	private int marks;
	private String subjectName;
	
	
	public submittedAssignments(int submitid, int studentid, String studentName, int assignmentid, String assignmentName,
			String uploadedFile, int marks) {
		super();
		this.submitid = submitid;
		this.studentid = studentid;
		this.studentName = studentName;
		this.assignmentid = assignmentid;
		this.assignmentName = assignmentName;
		UploadedFile = uploadedFile;
		this.marks = marks;
	}


	public int getSubmitid() {
		return submitid;
	}


	public int getStudentid() {
		return studentid;
	}


	public String getStudentName() {
		return studentName;
	}


	public int getAssignmentid() {
		return assignmentid;
	}


	public String getAssignmentName() {
		return assignmentName;
	}


	public String getUploadedFile() {
		return UploadedFile;
	}


	public int getMarks() {
		return marks;
	}


	public void setSubmitid(int submitid) {
		this.submitid = submitid;
	}


	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public void setAssignmentid(int assignmentid) {
		this.assignmentid = assignmentid;
	}


	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}


	public void setUploadedFile(String uploadedFile) {
		UploadedFile = uploadedFile;
	}


	public void setMarks(int marks) {
		this.marks = marks;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	



}
