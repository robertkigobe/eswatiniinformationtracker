package org.bantwana.controller;

import java.util.List;

import org.bantwana.entity.Attendance;
import org.bantwana.entity.Teacher;
import org.bantwana.service.AttendanceService;
import org.bantwana.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/attendanceAdmin")
public class AttendanceControllerAdmin {

	// need to inject our customer service
	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private TeacherService teacherService;

	// admin actions

	@GetMapping("/list_attendance_admin")
	public String listAdminAttendance(Model theModel) {
		
		List<String> theSchools = attendanceService.getSchools();
		List<String> theSubjects = attendanceService.getSubjects();
		List<String> theClasses = attendanceService.getClasses();
		List<String> theRegions = attendanceService.getRegions();
		
		List<Attendance> theAttendance = attendanceService.getAdminAttendance();
		theModel.addAttribute("schools", theSchools);
		theModel.addAttribute("subjects", theSubjects);
		theModel.addAttribute("classes", theClasses);
		theModel.addAttribute("regions", theRegions);
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());
		

		return "list_attendance_admin";
	}
	
	@GetMapping("/searchAdminAttendance")
	public String searchAdminAttendance(

			@RequestParam("studentschool") String studentschool, 
			@RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, 
			@RequestParam("studentsubject") String studentsubject,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,
			

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchAdminAttendance(studentschool, studentregion,
				studentform, fromDate, toDate, studentsubject);

		// add the customers to the model
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("fromDate", fromDate);
		theModel.addAttribute("toDate", toDate);
		theModel.addAttribute("studentform", studentform);
		theModel.addAttribute("studentregion", studentregion);
		theModel.addAttribute("studentschool", studentschool);
		theModel.addAttribute("studentsubject", studentsubject);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_AdminFiltered";
	}
	
	
	@GetMapping("/searchAdminAttendanceResetAll")
	public String searchAdminAttendanceResetAll(
			
			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,
			@RequestParam("studentsubject") String studentsubject,
			@RequestParam("action") String action,
			

			Model theModel) {
		
			attendanceService.ResetAllAttendance(studentschool, studentregion,studentform, fromDate, toDate,studentsubject);

			return "redirect:/attendanceAdmin/list_attendance_admin";
	}
	
	
	
	
	@GetMapping("/deleteAdminAttendance")
	public String deleteAdminAttendance(@RequestParam("attendanceId") int theId) {

		// update the customer
		attendanceService.deleteAdminAttendance(theId);

		return "redirect:/attendanceAdmin/list_attendance_admin";
	}





	@GetMapping("/reset")
	public String resetAdminAttendance(@RequestParam("attendanceId") int theId) {

		// update the customer
		attendanceService.resetAdminAttendance(theId);

		return "redirect:/attendance/list_attendance_admin";
	}

	@GetMapping("/list_head_teachers")
	public String listHeadTeacher(Model theModel) {

		List<Teacher> theTeachers = teacherService.getTeachers();
		theModel.addAttribute("teachers", theTeachers);

		return "list_teachers_admin";
	}
	

	@GetMapping("/admin_user_manual")
	public String AdminUserManual() {

		return "admin_user_manual";
	}
	
	@GetMapping("/list_dg_admin")
	public String listDg(Model theModel) {

		
		List<String> theSchools = attendanceService.getSchools();
		List<String> theClasses = attendanceService.getClasses();
		List<String> theRegions = attendanceService.getRegions();

		List<Attendance> theAttendance = attendanceService.getDg();
		
		theModel.addAttribute("schools", theSchools);
		theModel.addAttribute("classes", theClasses);
		theModel.addAttribute("regions", theRegions);
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_dg_admin";
	}
	
	
	@GetMapping("/searchAdminDg")
	public String searchAdminDg(

			@RequestParam("studentschool") String studentschool, 
			@RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, 
			@RequestParam("studentsubject") String studentsubject,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,
			

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchAdminAttendance(studentschool, studentregion,
				studentform, fromDate, toDate, studentsubject);

		// add the customers to the model
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("fromDate", fromDate);
		theModel.addAttribute("toDate", toDate);
		theModel.addAttribute("studentform", studentform);
		theModel.addAttribute("studentregion", studentregion);
		theModel.addAttribute("studentschool", studentschool);
		theModel.addAttribute("studentsubject", studentsubject);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_DgFiltered";
	}
	
	@GetMapping("/searchAdminDgResetAll")
	public String searchAdminDgResetAll(
			
			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,
			@RequestParam("studentsubject") String studentsubject,
			

			Model theModel) {

			// reset the group
		attendanceService.ResetAllAttendance(studentschool, studentregion,studentform, fromDate, toDate,studentsubject);

			return "redirect:/attendanceAdmin/list_dg_admin";
	}
	
	
	@GetMapping("/list_attendance_admin_approved")
	public String listAdminApproved(Model theModel) {

		List<Attendance> theAttendance = attendanceService.getApproved();
		theModel.addAttribute("attendance", theAttendance);

		return "list_attendance_admin_approved";
	}
	
	@GetMapping("/delete_list_admin")
	public String deleteListAdmin() {

		return "list_attendance_admin_delete";
	}
	
	@GetMapping("/adminAttendanceDeleteAllRange")
	public String adminAttendanceDeleteAllRange(
			
			
			@RequestParam("fromRange") Integer fromRange,
			@RequestParam("toRange") Integer toRange,
			
			

			Model theModel) {
		
			attendanceService.deleteAllAttendance(fromRange, toRange);

			return "redirect:/attendanceAdmin/list_attendance_admin";
	}
	
	

	

}
