package org.bantwana.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "deviceActionformattedDate")
	String deviceActionformattedDate;

	@Column(name = "deviceActionandroid_id")
	String deviceActionandroid_id;

	@Column(name = "deviceActionusername")
	String deviceActionusername;

	@Column(name = "deviceActionuserrole")
	String deviceActionuserrole;

	@Column(name = "deviceActionlatValue")
	String deviceActionlatValue;

	@Column(name = "deviceActionlngValue")
	String deviceActionlngValue;

	@Column(name = "deviceActionactions")
	String deviceActionactions;

	@Column(name = "teacherusername1t")
	String teacherusername1t;

	@Column(name = "teacherpint")
	String teacherpint;

	@Column(name = "teacherfirstnamet")
	String teacherfirstnamet;

	@Column(name = "teachersecondnamet")
	String teachersecondnamet;

	@Column(name = "teachersurnamet")
	String teachersurnamet;

	@Column(name = "teacherschoolt")
	String teacherschoolt;

	@Column(name = "dateOfAttendance")
	String dateOfAttendance;

	@Column(name = "dateOfImport")
	String dateOfImport;

	@Column(name = "studentpin")
	String studentpin;

	@Column(name = "studentfirstname")
	String studentfirstname;

	@Column(name = "studentregion")
	String studentregion;

	@Column(name = "studentsurname")
	String studentsurname;

	@Column(name = "studentschool")
	String studentschool;

	@Column(name = "studentgender")
	String studentgender;

	@Column(name = "studentdateofbirth")
	String studentdateofbirth;

	@Column(name = "studentform")
	String studentform;

	@Column(name = "studentstatus")
	String studentstatus;

	@Column(name = "studentsubject")
	String studentsubject;

	@Column(name = "studentattendance")
	String studentattendance;

	@Column(name = "flag")
	String flag;

	@Column(name = "meComment")
	String meComment;

	@Column(name = "meOfficer")
	String meOfficer;

	@Column(name = "supervisor")
	String supervisor;

	@Column(name = "admin")
	String admin;

	@Column(name = "adminComment")
	String adminComment;

	@Column(name = "supervisorComment")
	String supervisorComment;

	@Column(name = "adminCommentDate")
	String adminCommentDate;

	@Column(name = "supervisorCommentDate")
	String supervisorCommentDate;

	@Column(name = "meCommentDate")
	String meCommentDate;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceActionformattedDate() {
		return deviceActionformattedDate;
	}

	public void setDeviceActionformattedDate(String deviceActionformattedDate) {
		this.deviceActionformattedDate = deviceActionformattedDate;
	}

	public String getDeviceActionandroid_id() {
		return deviceActionandroid_id;
	}

	public void setDeviceActionandroid_id(String deviceActionandroid_id) {
		this.deviceActionandroid_id = deviceActionandroid_id;
	}

	public String getDeviceActionusername() {
		return deviceActionusername;
	}

	public void setDeviceActionusername(String deviceActionusername) {
		this.deviceActionusername = deviceActionusername;
	}

	public String getDeviceActionuserrole() {
		return deviceActionuserrole;
	}

	public void setDeviceActionuserrole(String deviceActionuserrole) {
		this.deviceActionuserrole = deviceActionuserrole;
	}

	public String getDeviceActionlatValue() {
		return deviceActionlatValue;
	}

	public void setDeviceActionlatValue(String deviceActionlatValue) {
		this.deviceActionlatValue = deviceActionlatValue;
	}

	public String getDeviceActionlngValue() {
		return deviceActionlngValue;
	}

	public void setDeviceActionlngValue(String deviceActionlngValue) {
		this.deviceActionlngValue = deviceActionlngValue;
	}

	public String getDeviceActionactions() {
		return deviceActionactions;
	}

	public void setDeviceActionactions(String deviceActionactions) {
		this.deviceActionactions = deviceActionactions;
	}

	public String getTeacherusername1t() {
		return teacherusername1t;
	}

	public void setTeacherusername1t(String teacherusername1t) {
		this.teacherusername1t = teacherusername1t;
	}

	public String getTeacherpint() {
		return teacherpint;
	}

	public void setTeacherpint(String teacherpint) {
		this.teacherpint = teacherpint;
	}

	public String getTeacherfirstnamet() {
		return teacherfirstnamet;
	}

	public void setTeacherfirstnamet(String teacherfirstnamet) {
		this.teacherfirstnamet = teacherfirstnamet;
	}

	public String getTeachersecondnamet() {
		return teachersecondnamet;
	}

	public void setTeachersecondnamet(String teachersecondnamet) {
		this.teachersecondnamet = teachersecondnamet;
	}

	public String getTeachersurnamet() {
		return teachersurnamet;
	}

	public void setTeachersurnamet(String teachersurnamet) {
		this.teachersurnamet = teachersurnamet;
	}

	public String getTeacherschoolt() {
		return teacherschoolt;
	}

	public void setTeacherschoolt(String teacherschoolt) {
		this.teacherschoolt = teacherschoolt;
	}

	public String getDateOfAttendance() {
		return dateOfAttendance;
	}

	public void setDateOfAttendance(String dateOfAttendance) {
		this.dateOfAttendance = dateOfAttendance;
	}

	public String getDateOfImport() {
		return dateOfImport;
	}

	public void setDateOfImport(String dateOfImport) {
		this.dateOfImport = dateOfImport;
	}

	public String getStudentpin() {
		return studentpin;
	}

	public void setStudentpin(String studentpin) {
		this.studentpin = studentpin;
	}

	public String getStudentfirstname() {
		return studentfirstname;
	}

	public void setStudentfirstname(String studentfirstname) {
		this.studentfirstname = studentfirstname;
	}

	public String getStudentregion() {
		return studentregion;
	}

	public void setStudentregion(String studentregion) {
		this.studentregion = studentregion;
	}

	public String getStudentsurname() {
		return studentsurname;
	}

	public void setStudentsurname(String studentsurname) {
		this.studentsurname = studentsurname;
	}

	public String getStudentschool() {
		return studentschool;
	}

	public void setStudentschool(String studentschool) {
		this.studentschool = studentschool;
	}

	public String getStudentgender() {
		return studentgender;
	}

	public void setStudentgender(String studentgender) {
		this.studentgender = studentgender;
	}

	public String getStudentdateofbirth() {
		return studentdateofbirth;
	}

	public void setStudentdateofbirth(String studentdateofbirth) {
		this.studentdateofbirth = studentdateofbirth;
	}

	public String getStudentform() {
		return studentform;
	}

	public void setStudentform(String studentform) {
		this.studentform = studentform;
	}

	public String getStudentstatus() {
		return studentstatus;
	}

	public void setStudentstatus(String studentstatus) {
		this.studentstatus = studentstatus;
	}

	public String getStudentsubject() {
		return studentsubject;
	}

	public void setStudentsubject(String studentsubject) {
		this.studentsubject = studentsubject;
	}

	public String getStudentattendance() {
		return studentattendance;
	}

	public void setStudentattendance(String studentattendance) {
		this.studentattendance = studentattendance;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMeComment() {
		return meComment;
	}

	public void setMeComment(String meComment) {
		this.meComment = meComment;
	}

	public String getMeOfficer() {
		return meOfficer;
	}

	public void setMeOfficer(String meOfficer) {
		this.meOfficer = meOfficer;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}

	public String getSupervisorComment() {
		return supervisorComment;
	}

	public void setSupervisorComment(String supervisorComment) {
		this.supervisorComment = supervisorComment;
	}

	public String getAdminCommentDate() {
		return adminCommentDate;
	}

	public void setAdminCommentDate(String adminCommentDate) {
		this.adminCommentDate = adminCommentDate;
	}

	public String getSupervisorCommentDate() {
		return supervisorCommentDate;
	}

	public void setSupervisorCommentDate(String supervisorCommentDate) {
		this.supervisorCommentDate = supervisorCommentDate;
	}

	public String getMeCommentDate() {
		return meCommentDate;
	}

	public void setMeCommentDate(String meCommentDate) {
		this.meCommentDate = meCommentDate;
	}

	public Attendance() {

	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", deviceActionformattedDate=" + deviceActionformattedDate
				+ ", deviceActionandroid_id=" + deviceActionandroid_id + ", deviceActionusername="
				+ deviceActionusername + ", deviceActionuserrole=" + deviceActionuserrole + ", deviceActionlatValue="
				+ deviceActionlatValue + ", deviceActionlngValue=" + deviceActionlngValue + ", deviceActionactions="
				+ deviceActionactions + ", teacherusername1t=" + teacherusername1t + ", teacherpint=" + teacherpint
				+ ", teacherfirstnamet=" + teacherfirstnamet + ", teachersecondnamet=" + teachersecondnamet
				+ ", teachersurnamet=" + teachersurnamet + ", teacherschoolt=" + teacherschoolt + ", dateOfAttendance="
				+ dateOfAttendance + ", dateOfImport=" + dateOfImport + ", studentpin=" + studentpin
				+ ", studentfirstname=" + studentfirstname + ", studentregion=" + studentregion + ", studentsurname="
				+ studentsurname + ", studentschool=" + studentschool + ", studentgender=" + studentgender
				+ ", studentdateofbirth=" + studentdateofbirth + ", studentform=" + studentform + ", studentstatus="
				+ studentstatus + ", studentsubject=" + studentsubject + ", studentattendance=" + studentattendance
				+ ", flag=" + flag + ", meComment=" + meComment + ", meOfficer=" + meOfficer + ", supervisor="
				+ supervisor + ", admin=" + admin + ", adminComment=" + adminComment + ", supervisorComment="
				+ supervisorComment + ", adminCommentDate=" + adminCommentDate + ", supervisorCommentDate="
				+ supervisorCommentDate + ", meCommentDate=" + meCommentDate + "]";
	}

	public Attendance(int id, String deviceActionformattedDate, String deviceActionandroid_id,
			String deviceActionusername, String deviceActionuserrole, String deviceActionlatValue,
			String deviceActionlngValue, String deviceActionactions, String teacherusername1t, String teacherpint,
			String teacherfirstnamet, String teachersecondnamet, String teachersurnamet, String teacherschoolt,
			String dateOfAttendance, String dateOfImport, String studentpin, String studentfirstname,
			String studentregion, String studentsurname, String studentschool, String studentgender,
			String studentdateofbirth, String studentform, String studentstatus, String studentsubject,
			String studentattendance, String flag, String meComment, String meOfficer, String supervisor, String admin,
			String adminComment, String supervisorComment, String adminCommentDate, String supervisorCommentDate,
			String meCommentDate) {
		super();
		this.id = id;
		this.deviceActionformattedDate = deviceActionformattedDate;
		this.deviceActionandroid_id = deviceActionandroid_id;
		this.deviceActionusername = deviceActionusername;
		this.deviceActionuserrole = deviceActionuserrole;
		this.deviceActionlatValue = deviceActionlatValue;
		this.deviceActionlngValue = deviceActionlngValue;
		this.deviceActionactions = deviceActionactions;
		this.teacherusername1t = teacherusername1t;
		this.teacherpint = teacherpint;
		this.teacherfirstnamet = teacherfirstnamet;
		this.teachersecondnamet = teachersecondnamet;
		this.teachersurnamet = teachersurnamet;
		this.teacherschoolt = teacherschoolt;
		this.dateOfAttendance = dateOfAttendance;
		this.dateOfImport = dateOfImport;
		this.studentpin = studentpin;
		this.studentfirstname = studentfirstname;
		this.studentregion = studentregion;
		this.studentsurname = studentsurname;
		this.studentschool = studentschool;
		this.studentgender = studentgender;
		this.studentdateofbirth = studentdateofbirth;
		this.studentform = studentform;
		this.studentstatus = studentstatus;
		this.studentsubject = studentsubject;
		this.studentattendance = studentattendance;
		this.flag = flag;
		this.meComment = meComment;
		this.meOfficer = meOfficer;
		this.supervisor = supervisor;
		this.admin = admin;
		this.adminComment = adminComment;
		this.supervisorComment = supervisorComment;
		this.adminCommentDate = adminCommentDate;
		this.supervisorCommentDate = supervisorCommentDate;
		this.meCommentDate = meCommentDate;
	}

	

}
