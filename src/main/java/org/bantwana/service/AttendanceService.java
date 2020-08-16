package org.bantwana.service;

import java.util.List;

import org.bantwana.entity.Attendance;
import org.bantwana.entity.AttendanceStatistics;
import org.bantwana.entity.AttendanceSubmissionStatistics;

public interface AttendanceService {

	public List<Attendance> getAdminAttendance();

	public void resetAdminAttendance(int theId);

	public List<Attendance> getDg();


	////admin
	public List<Attendance> getMeAttendance();	
	
	public List<Attendance> searchAdminAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject);
	
	public void ResetAllAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject);
	
	public void deleteAdminAttendance(int theId);

	public void deleteAllAttendance(Integer fromRange, Integer toRange);
	
	
	public List<Attendance> getApproved();
	
	////monitoring

	public void verifyAllMe(String studentschool, String studentregion, String studentform, String fromDate, String toDate,
			String studentsubject);

	public List<Attendance> searchMeAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject);


	public Attendance getAttendance(int theId);

	
	public void saveAttendance(Attendance theAttendance);

	public void pendAttendanceMe(Attendance theAttendance);

	public List<Attendance> getMePendingAttendance();

	public List<Attendance> getResetMeAttendance();
	
	public List<Attendance> getMeDg();

	public void deleteAllAttendance(String studentschool, String studentregion, String studentform, String fromDate, String toDate, String studentsubject);
	
	public List<String> getMeSubjects();
	
	public void resetPendingMeAttendance(int theId);

	public void verifyAdminAttendance(int theId);
	
	////supervisor
	public List<Attendance> searchSuAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject);

	public List<Attendance> getSuAttendance();

	public void approveAllSu(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject);

	public void resetSuAttendance(int theId, String supervisorComment, String supervisorCommentDate);

	public List<Attendance> getSuDg();

	public List<Attendance> getMePendingDg();
	
	public List<Attendance> getSuAttendanceVerified();
	///////reports
	
	public List<AttendanceStatistics> getAdminAttendanceCountPresent();
	
	public List<AttendanceStatistics> getAdminAttendanceCountAbsent();

	public List<Attendance> getSuAttendanceApproved();

	public List<Attendance> searchApprovedAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate);

	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionCountPresent();

	public List<AttendanceSubmissionStatistics> getAdminDgSubmissionCountPresent();

	public List<String> getSchools();

	public List<String> getSubjects();

	public List<String> getClasses();

	public List<String> getRegions();

	public List<AttendanceStatistics> getAdminAttendanceSearchAbsent(String fromDate, String toDate, String studentschool);

	public List<AttendanceStatistics> getAdminAttendanceSearchPresent(String fromDate, String toDate,
			String studentschool);

	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionApproved();

	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionVeified();







	









}
