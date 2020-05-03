package com.demo.api.pojo;

public class StudentPojo {
	private Integer studentId;
	private String studentName;

	public StudentPojo(Integer studentId, String studentName) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Override
	public String toString() {
		return "StudentPojo [studentId=" + studentId + ", studentName=" + studentName + "]";
	}

}
