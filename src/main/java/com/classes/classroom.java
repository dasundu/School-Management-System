package com.classes;

public class classroom {

	private int classid;
	private String className;
	private String roomNum;
	private String assignedTeacher;
	private String representative;
	private int numberOfStudents;

	public classroom(int classid, String className, String roomNum, String assignedTeacher, String representative,
			int numberOfStudents) {

		this.classid = classid;
		this.className = className;
		this.roomNum = roomNum;
		this.assignedTeacher = assignedTeacher;
		this.representative = representative;
		this.numberOfStudents = numberOfStudents;
	}

	public int getClassid() {
		return classid;
	}

	public String getClassName() {
		return className;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public String getAssignedTeacher() {
		return assignedTeacher;
	}

	public String getRepresentative() {
		return representative;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public void setAssignedTeacher(String assignedTeacher) {
		this.assignedTeacher = assignedTeacher;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

}
