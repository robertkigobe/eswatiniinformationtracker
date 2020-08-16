package org.bantwana.controller;

import java.util.List;

import org.bantwana.entity.Attendance;
import org.bantwana.entity.AttendanceStatistics;
import org.bantwana.entity.AttendanceSubmissionStatistics;
import org.bantwana.entity.School;
import org.bantwana.entity.Student;
import org.bantwana.entity.Teacher;
import org.bantwana.service.AttendanceService;
import org.bantwana.service.SchoolService;
import org.bantwana.service.StudentService;
import org.bantwana.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/attendanceReport")
public class AttendanceControllerReport {

	// need to inject our customer service
	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SchoolService schoolService;

	

	/// admin_attendance_statistics_present //////
	
	
	@GetMapping("/attendance_statistics_present")
	public String attendanceStatisticsPresent(Model theModel) {
		List<String> theSchools = attendanceService.getSchools();
		List<AttendanceStatistics> theAttendanceCount = attendanceService.getAdminAttendanceCountPresent();
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Present");
		theModel.addAttribute("schools", theSchools);
		return "admin_attendance_statistics_present";
	}
	
	@GetMapping("/searchAttendanceStatisticsPresent")
	public String searchAttendanceStatisticsPresent(
			@RequestParam("fromDate") String fromDate, 
			@RequestParam("toDate") String toDate,
			@RequestParam("studentschool") String studentschool,
			
			Model theModel) {

		List<AttendanceStatistics> theAttendanceCount = attendanceService.getAdminAttendanceSearchPresent(fromDate, toDate, studentschool);
		
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Absent");

		return "admin_attendance_statistics_present";
	}
	
	
	
	
	/// admin_attendance_statistics_Absent //////
	
	
	@GetMapping("/attendance_statistics_absent")
	public String attendanceStatisticsAbsent(Model theModel) {
		List<String> theSchools = attendanceService.getSchools();
		List<AttendanceStatistics> theAttendanceCount = attendanceService.getAdminAttendanceCountAbsent();
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Absent");
		theModel.addAttribute("schools", theSchools);

		return "admin_attendance_statistics_absent";
	}
	
	@GetMapping("/searchAttendanceStatisticsAbsent")
	public String searchAttendanceStatisticsAbsent(
			@RequestParam("fromDate") String fromDate, 
			@RequestParam("toDate") String toDate,
			@RequestParam("studentschool") String studentschool,
			
			Model theModel) {

		List<AttendanceStatistics> theAttendanceCount = attendanceService.getAdminAttendanceSearchAbsent(fromDate, toDate, studentschool);
		
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Absent");

		return "admin_attendance_statistics";
	}
	
	
	
	@GetMapping("/list_student_admin")
	public String listStudent(Model theModel) {

		List<Student> theStudents = studentService.getStudents();
		theModel.addAttribute("students", theStudents);

		return "list_students_admin";
	}

	@GetMapping("/list_school_admin")
	public String listSchool(Model theModel) {

		List<School> theSchools = schoolService.getSchools();
		theModel.addAttribute("schools", theSchools);

		return "list_school_admin";
	}
	
	@GetMapping("/list_teacher_admin")
	public String listTeacher(Model theModel) {

		List<Teacher> theTeachers = teacherService.getTeachers();
		theModel.addAttribute("teachers", theTeachers);

		return "list_teachers_admin";
	}
	
	@GetMapping("/showFormForUpdateTeacher")
	public String showFormForUpdate(@RequestParam("teacherId") int theId, Model theModel) {

		// get the customer from our service
		Teacher theTeacher = teacherService.getTeacher(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("teacher", theTeacher);

		// send over to our form
		return "teacher-form";
	}
	
	@GetMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("teacherId") int theId) {

		// delete the customer
		teacherService.deleteTeacher(theId);

		return "redirect:/attendanceReport/list_teacher_admin";
	}
	
	@GetMapping("/showFormForAddTeacher")
	public String showFormForAddTeacher(Model theModel) {

		// create model attribute to bind form data
		Teacher theTeacher = new Teacher();

		theModel.addAttribute("teacher", theTeacher);

		return "teacher-form";
	}

	@PostMapping("/saveTeacher")
	public String saveTeacher(@ModelAttribute("teacher") Teacher theTeacher) {

		// save the customer using our service
		teacherService.saveTeacher(theTeacher);

		return "redirect:/attendanceReport/list_teacher_admin";
	}
	
	@GetMapping("/attendance_statistics_submitted")
	public String attendanceStatisticsSubmitted(Model theModel) {

		List<AttendanceSubmissionStatistics> theAttendanceCount = attendanceService.getAdminAttendanceSubmissionCountPresent();
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Present");

		return "admin_attendance_statistics_submitted";
	}
	
	@GetMapping("/dg_statistics_submitted")
	public String dgStatisticsSubmitted(Model theModel) {

		List<AttendanceSubmissionStatistics> theAttendanceCount = attendanceService.getAdminDgSubmissionCountPresent();
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Present");

		return "admin_dg_statistics_submitted";
	}
	
	
	@GetMapping("/attendance_statistics_submitted_approved")
	public String attendanceStatisticsSubmittedApproved(Model theModel) {

		List<AttendanceSubmissionStatistics> theAttendanceCount = attendanceService.getAdminAttendanceSubmissionApproved();
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Present");

		return "admin_attendance_statistics_submitted_approved";
	}
	
	
	@GetMapping("/attendance_statistics_submitted_verified")
	public String attendanceStatisticsSubmittedVerified(Model theModel) {

		List<AttendanceSubmissionStatistics> theAttendanceCount = attendanceService.getAdminAttendanceSubmissionVeified();
		theModel.addAttribute("attendanceCount", theAttendanceCount);
		theModel.addAttribute("attendance", "Present");

		return "admin_attendance_statistics_submitted_verified";
	}
	

}
