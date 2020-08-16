package org.bantwana.entity;

public class AttendanceStatistics {



	String deviceActionformattedDate;
	

	String studentregion;
	

	String studentschool;

	String studentgender;


	String studentform;

	String studentstatus;

	String studentsubject;

	String studentattendance;

	String flag;

	


	public void setDeviceActionformattedDate(String deviceActionformattedDate) {
		this.deviceActionformattedDate = deviceActionformattedDate;
	}



	public void setStudentregion(String studentregion) {
		this.studentregion = studentregion;
	}



	public void setStudentschool(String studentschool) {
		this.studentschool = studentschool;
	}



	public void setStudentgender(String studentgender) {
		this.studentgender = studentgender;
	}



	public void setStudentform(String studentform) {
		this.studentform = studentform;
	}



	public void setStudentstatus(String studentstatus) {
		this.studentstatus = studentstatus;
	}



	public void setStudentsubject(String studentsubject) {
		this.studentsubject = studentsubject;
	}



	public void setStudentattendance(String studentattendance) {
		this.studentattendance = studentattendance;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getDeviceActionformattedDate() {
		return deviceActionformattedDate;
	}



	public String getStudentregion() {
		return studentregion;
	}



	public String getStudentschool() {
		return studentschool;
	}



	public String getStudentgender() {
		return studentgender;
	}



	public String getStudentform() {
		return studentform;
	}



	public String getStudentstatus() {
		return studentstatus;
	}


	public String getStudentsubject() {
		return studentsubject;
	}



	public String getStudentattendance() {
		return studentattendance;
	}

	

	public String getFlag() {
		return flag;
	}



	@Override
	public String toString() {
		
		return "AttendanceStatistics [deviceActionformattedDate=" + deviceActionformattedDate + ", studentregion="
				+ studentregion + ", studentschool=" + studentschool + ", studentgender=" + studentgender
				+ ", studentform=" + studentform + ", studentstatus=" + studentstatus + ", studentsubject="
				+ studentsubject + ", studentattendance=" + studentattendance + ", flag=" + flag + "]";
	}



	public AttendanceStatistics() {
		
	}



	public AttendanceStatistics(String deviceActionformattedDate, String studentregion, String studentschool,
			String studentgender, String studentform, String studentstatus, String studentsubject,
			String studentattendance, String flag) {
		
		this.deviceActionformattedDate = deviceActionformattedDate;
		this.studentregion = studentregion;
		this.studentschool = studentschool;
		this.studentgender = studentgender;
		this.studentform = studentform;
		this.studentstatus = studentstatus;
		this.studentsubject = studentsubject;
		this.studentattendance = studentattendance;
		this.flag = flag;
	}
	
	


}
