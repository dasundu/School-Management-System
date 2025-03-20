package com.subjects;

public class subject {

	private int SubjectId;
	private String SubName;
	private String Description;
	private int NumOfHours;
	private String EnrollmentKey;

	public int getSubjectId() {
		return SubjectId;
	}

	public String getSubName() {
		return SubName;
	}

	public String getDescription() {
		return Description;
	}

	public int getNumOfHours() {
		return NumOfHours;
	}

	public void setSubjectId(int subjectId) {
		SubjectId = subjectId;
	}

	public void setSubName(String subName) {
		SubName = subName;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setNumOfHours(int numOfHours) {
		NumOfHours = numOfHours;
	}

	public subject(int subjectId, String subName, String description, int numOfHours, String EnrollmentKey) {

		SubjectId = subjectId;
		SubName = subName;
		Description = description;
		NumOfHours = numOfHours;
		this.EnrollmentKey = EnrollmentKey;
	}

	public subject() {
		// TODO Auto-generated constructor stub
	}

	public String getEnrollmentKey() {
		return EnrollmentKey;
	}

	public void setEnrollmentKey(String enrollmentKey) {
		EnrollmentKey = enrollmentKey;
	}

}
