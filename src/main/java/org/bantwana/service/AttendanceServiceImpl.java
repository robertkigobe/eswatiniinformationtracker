package org.bantwana.service;

import java.util.List;

import org.bantwana.dao.AttendanceDAO;
import org.bantwana.entity.Attendance;
import org.bantwana.entity.AttendanceStatistics;
import org.bantwana.entity.AttendanceSubmissionStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	private AttendanceDAO attendanceDAO;
	
	/////admin

	@Override
	@Transactional
	public List<Attendance> getAdminAttendance() {
		
		return attendanceDAO.getAdminAttendance();
	}

	@Override
	@Transactional
	public void deleteAdminAttendance(int theId) {
		
		attendanceDAO.deleteAdminAttendance(theId);
		
	}

	@Override
	@Transactional
	public void resetAdminAttendance(int theId) {
		
		attendanceDAO.resetAdminAttendance(theId);
		
	}

	@Override
	@Transactional
	public List<Attendance> getDg() {
		// TODO Auto-generated method stub
		return attendanceDAO.getDg();
	}
	
	
	@Override
	@Transactional
	public void deleteAllAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {
		

		attendanceDAO.deleteAllAttendance(studentschool,studentregion, studentform,fromDate, toDate, studentsubject);
		
	}
	

	@Override
	@Transactional
	public List<Attendance> searchAdminAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject) {
		
		return attendanceDAO.searchAdminAttendance(studentschool,studentregion, studentform,fromDate, toDate, studentsubject);
	}
	
	@Override
	@Transactional
	public void ResetAllAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {
		attendanceDAO.resetAllAttendance(studentschool,studentregion, studentform,fromDate, toDate, studentsubject);
		
	}
	
	@Override
	@Transactional
	public void deleteAllAttendance(Integer fromRange, Integer toRange) {
		attendanceDAO.deleteAllAttendance(fromRange,toRange);
		
	}
	
	////monitoring

	@Override
	@Transactional
	public List<Attendance> getMeAttendance() {
		
		return attendanceDAO.getMeAttendance();
	}

	@Override
	@Transactional
	public void verifyAllMe(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {
		
		attendanceDAO.verifyAllMe(studentschool,studentregion, studentform,fromDate, toDate, studentsubject);
		
	}

	@Override
	@Transactional
	public List<Attendance> searchMeAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject) {
		

		return attendanceDAO.searchMeAttendance(studentschool,studentregion, studentform,fromDate, toDate,studentsubject);
	}
	
	@Override
	@Transactional
	public Attendance getAttendance(int theId) {
		

		return attendanceDAO.getAttendance(theId);
	}
	
	@Override
	@Transactional
	public void saveAttendance(Attendance theAttendance) {
		attendanceDAO.saveAttendance(theAttendance);
	}

	@Override
	@Transactional
	public void pendAttendanceMe(Attendance theAttendance) {
		attendanceDAO.pendAttendanceMe(theAttendance);
		
	}
	
	@Override
	@Transactional
	public List<Attendance> getMePendingAttendance() {
		
		return attendanceDAO.getMePendingAttendance();
		
	}

	
	@Override
	@Transactional
	public List<Attendance> getResetMeAttendance() {
		
		return attendanceDAO.getResetMeAttendance();
		
	}
	
	@Override
	@Transactional
	public List<Attendance> getMePendingDg() {
		
		return attendanceDAO.getMePendingDg();
	}
	
	
	@Override
	@Transactional
	public List<String> getMeSubjects() {
		
		return attendanceDAO.getMeSubjects();
	}

	

	@Override
	@Transactional
	public void resetPendingMeAttendance(int theId) {
		
		attendanceDAO.resetAdminAttendance(theId);
		
	}
	
	@Override
	@Transactional
	public void verifyAdminAttendance(int theId) {
		
		attendanceDAO.verifyAdminAttendance(theId);
		
	}
	
	
	/////supervisor

	@Override
	@Transactional
	public List<Attendance> searchSuAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject) {
		
		return attendanceDAO.searchSuAttendance(studentschool,studentregion, studentform,fromDate, toDate,studentsubject);
		
	}
	
	

	@Override
	@Transactional
	public List<Attendance> getSuAttendance() {
		
		return attendanceDAO.getSuAttendance();
	}

	@Override
	@Transactional
	public void approveAllSu(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {

		attendanceDAO.approveAllSu(studentschool,studentregion, studentform,fromDate, toDate, studentsubject);

		
	}


	@Override
	@Transactional
	public void resetSuAttendance(int theId, String supervisorComment, String supervisorCommentDate) {
		

		attendanceDAO.resetSuAttendanceMe(theId,supervisorComment,supervisorCommentDate);
		
	}

	@Override
	@Transactional
	public List<Attendance> getMeDg() {
		
		return attendanceDAO.getMeDg();
		
	}

	@Override
	@Transactional
	public List<Attendance> getSuDg() {
		return attendanceDAO.getSuDg();
	}



	@Override
	@Transactional
	public List<Attendance> getApproved() {
		

		return attendanceDAO.getApproved();
	}
	
	@Override
	@Transactional
	public List<Attendance> getSuAttendanceVerified() {
		

		return attendanceDAO.getVerified();
	}


	/////reports
	
	@Override
	@Transactional
	public List<AttendanceStatistics> getAdminAttendanceCountPresent() {
		
		return attendanceDAO.getAdminAttendanceCountPresent();
		
	}
	
	@Override
	@Transactional
	public List<AttendanceStatistics> getAdminAttendanceCountAbsent() {
		
		return attendanceDAO.getAdminAttendanceCountAbsent();
		
	}

	@Override
	@Transactional
	public List<Attendance> getSuAttendanceApproved() {
		// TODO Auto-generated method stub
		return attendanceDAO.getSuAttendanceApproved();
	}

	@Override
	@Transactional
	public List<Attendance> searchApprovedAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate) {
		
		return attendanceDAO.approvedAttendance(studentschool,studentregion, studentform,fromDate, toDate);
		
	}

	@Override
	@Transactional
	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionCountPresent() {
		// TODO Auto-generated method stub
		return attendanceDAO.getAdminAttendanceCountSubmitted();
	}

	@Override
	@Transactional
	public List<AttendanceSubmissionStatistics> getAdminDgSubmissionCountPresent() {
		// TODO Auto-generated method stub
				return attendanceDAO.getAdminDgCountSubmitted();
	}

	@Override
	@Transactional
	public List<String> getSchools() {
		return attendanceDAO.getSchools();
	}

	@Override
	@Transactional
	public List<String> getSubjects() {
		return attendanceDAO.getSubjects();
	}

	@Override
	@Transactional
	public List<String> getClasses() {
		return attendanceDAO.getClasses();
	}

	@Override
	@Transactional
	public List<String> getRegions() {
		return attendanceDAO.getRegions();
	}

	@Override
	@Transactional
	public List<AttendanceStatistics> getAdminAttendanceSearchAbsent(String fromDate, String toDate,
			String studentschool) {
		return attendanceDAO.getAdminAttendanceSearchAbsent(fromDate, toDate, studentschool);
	}

	@Override
	@Transactional
	public List<AttendanceStatistics> getAdminAttendanceSearchPresent(String fromDate, String toDate,
			String studentschool) {
		return attendanceDAO.getAdminAttendanceSearchPresent(fromDate, toDate, studentschool);
	}

	@Override
	@Transactional
	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionApproved() {
		
		return attendanceDAO.getAdminAttendanceSubmissionApproved();
	}

	@Override
	@Transactional
	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionVeified() {
		
		return attendanceDAO.getAdminAttendanceSubmissionVeified();

	}





}
