package com.results;

public class result {
	private int resultid;
	private String ResultDescription;
	private String className;
	private String SubjectName;
	private String uploaderName;
	private String sheet;
	private String releasedDate;

	public int getResultid() {
		return resultid;
	}

	public String getResultDescription() {
		return ResultDescription;
	}

	public String getClassName() {
		return className;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public String getUploaderName() {
		return uploaderName;
	}

	public String getSheet() {
		return sheet;
	}

	public String getReleasedDate() {
		return releasedDate;
	}

	public void setResultid(int resultid) {
		this.resultid = resultid;
	}

	public void setResultDescription(String resultDescription) {
		ResultDescription = resultDescription;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	public result(int resultid, String resultDescription, String className, String subjectName, String uploaderName,
			String sheet, String releasedDate) {

		this.resultid = resultid;
		ResultDescription = resultDescription;
		this.className = className;
		SubjectName = subjectName;
		this.uploaderName = uploaderName;
		this.sheet = sheet;
		this.releasedDate = releasedDate;
	}

}
