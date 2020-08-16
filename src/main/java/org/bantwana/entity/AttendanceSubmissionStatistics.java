package org.bantwana.entity;

public class AttendanceSubmissionStatistics {



	String deviceActionformattedDate;
	

	String studentregion;
	

	String studentschool;

	String studentform;
	
	String studentsubject;


	String flag;


	public String getDeviceActionformattedDate() {
		return deviceActionformattedDate;
	}


	public void setDeviceActionformattedDate(String deviceActionformattedDate) {
		this.deviceActionformattedDate = deviceActionformattedDate;
	}


	public String getStudentregion() {
		return studentregion;
	}


	public void setStudentregion(String studentregion) {
		this.studentregion = studentregion;
	}


	public String getStudentschool() {
		return studentschool;
	}


	public void setStudentschool(String studentschool) {
		this.studentschool = studentschool;
	}


	public String getStudentform() {
		return studentform;
	}


	public void setStudentform(String studentform) {
		this.studentform = studentform;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getStudentsubject() {
		return studentsubject;
	}


	public void setStudentsubject(String studentsubject) {
		this.studentsubject = studentsubject;
	}


	public AttendanceSubmissionStatistics(String deviceActionformattedDate, String studentregion, String studentschool,
			String studentform, String studentsubject, String flag) {
		
		this.deviceActionformattedDate = deviceActionformattedDate;
		this.studentregion = studentregion;
		this.studentschool = studentschool;
		this.studentform = studentform;
		this.studentsubject = studentsubject;
		this.flag = flag;
	}


	public AttendanceSubmissionStatistics() {
		// TODO Auto-generated constructor stub
	}


	


}
