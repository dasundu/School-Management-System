package com.assignments;

public class assignment {

	private int assignmentId;
	private String title;
	private int subjectid;
	private int classid;
	private String pdfFile;
	private String uploader;

	public int getAssignmentId() {
		return assignmentId;
	}

	public String getTitle() {
		return title;
	}

	public int getSubjectid() {
		return subjectid;
	}

	public int getClassid() {
		return classid;
	}

	public String getPdfFile() {
		return pdfFile;
	}

	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}

	public assignment(int assignmentId, String title, int subjectid, int classid, String pdfFile, String uploader) {

		this.assignmentId = assignmentId;
		this.title = title;
		this.subjectid = subjectid;
		this.classid = classid;
		this.pdfFile = pdfFile;
		this.setUploader(uploader);
	}

	public assignment() {
		// TODO Auto-generated constructor stub
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

}
