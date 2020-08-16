package org.bantwana.dao;

import java.util.List;

import org.bantwana.entity.Attendance;
import org.bantwana.entity.AttendanceStatistics;
import org.bantwana.entity.AttendanceSubmissionStatistics;

public interface AttendanceDAO {


	
	//////admin
	public List<Attendance> getAdminAttendance();

	public void resetAllAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject);
	
	public void deleteAdminAttendance(int theId);
	
	public void resetAdminAttendance(int theId);
	
	public List<Attendance> getDg();

	public void deleteAllAttendance(String studentschool, String studentregion, String studentform, String fromDate, String toDate, String studentsubject);

	public List<Attendance> getApproved();

	/////monitoring
	public List<Attendance> searchAdminAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject);
	
	public List<Attendance> getMeAttendance();
	
	public void verifyAllMe(String studentschool, String studentregion, String studentform, String fromDate, String toDate, String studentsubject);
	
	public List<Attendance> searchMeAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject);
		
	public Attendance getAttendance(int theId);

	public void saveAttendance(Attendance theAttendance);

	public void pendAttendanceMe(Attendance theAttendance);

	public List<Attendance> getMePendingAttendance();

	public List<Attendance> getResetMeAttendance();
	
	public List<Attendance> getMeDg();
	
	public List<Attendance> getMePendingDg();

	public List<String> getMeSubjects();
	
	public void verifyAdminAttendance(int theId);

	//////supervisor
	
	public List<Attendance> searchSuAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject);

	public List<Attendance> getSuAttendance();

	public void approveAllSu(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject);

	public void resetSuAttendanceMe(int theId, String supervisorComment, String supervisorCommentDate);

	public List<Attendance> getSuDg();
	

	public List<Attendance> getVerified();

	/////report
	
	public List<AttendanceStatistics> getAdminAttendanceCountPresent();

	public List<AttendanceStatistics> getAdminAttendanceCountAbsent();

	public List<Attendance> getSuAttendanceApproved();

	public List<Attendance> approvedAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate);

	public List<AttendanceSubmissionStatistics> getAdminAttendanceCountSubmitted();

	public List<AttendanceSubmissionStatistics> getAdminDgCountSubmitted();

	public List<String> getSchools();

	public List<String> getSubjects();

	public List<String> getClasses();

	public List<String> getRegions();

	public void deleteAllAttendance(Integer fromRange, Integer toRange);

	public List<AttendanceStatistics> getAdminAttendanceSearchAbsent(String fromDate, String toDate,
			String studentschool);

	public List<AttendanceStatistics> getAdminAttendanceSearchPresent(String fromDate, String toDate,
			String studentschool);

	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionApproved();

	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionVeified();













}
