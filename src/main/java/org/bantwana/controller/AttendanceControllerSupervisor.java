package org.bantwana.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.bantwana.entity.Attendance;
import org.bantwana.service.AttendanceService;
import org.bantwana.view.AttendanceExcelReportView;
import org.bantwana.view.AttendancePdfReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/attendanceSupervisor")
public class AttendanceControllerSupervisor {

	// need to inject our customer service
	@Autowired
	private AttendanceService attendanceService;

	// monitoring actions

	@GetMapping("/list_attendance_su")
	public String listSuAttendance(Model theModel) {
		
		List<String> theSchools = attendanceService.getSchools();
		List<String> theClasses = attendanceService.getClasses();
		List<String> theRegions = attendanceService.getRegions();
		List<String> theSubjects = attendanceService.getSubjects();

		List<Attendance> theAttendance = attendanceService.getSuAttendance();
		theModel.addAttribute("schools", theSchools);
		theModel.addAttribute("classes", theClasses);
		theModel.addAttribute("regions", theRegions);
		theModel.addAttribute("subjects", theSubjects);
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_su";
	}

	@GetMapping("/searchSuAttendance")
	public String searchSuAttendance(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("studentsubject") String studentsubject,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchSuAttendance(studentschool, studentregion, studentform,
				fromDate, toDate, studentsubject);

		// add the customers to the model
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("fromDate", fromDate);
		theModel.addAttribute("toDate", toDate);
		theModel.addAttribute("studentform", studentform);
		theModel.addAttribute("studentregion", studentregion);
		theModel.addAttribute("studentschool", studentschool);
		theModel.addAttribute("studentsubject", studentsubject);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_suFiltered";
	}

	@GetMapping("/searchSuAttendanceApproveAll")
	public String searchSuAttendanceApproveAll(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("studentsubject") String studentsubject,

			Model theModel) {

		// delete the customer
		attendanceService.approveAllSu(studentschool, studentregion, studentform, fromDate, toDate, studentsubject);

		return "redirect:/attendanceSupervisor/list_attendance_su";
	}

	@GetMapping("/showFormForResetAttendance")
	public String showFormForPackAttendance(@RequestParam("attendanceId") int theId, Model theModel) {

		// get the customer from our service
		Attendance theAttendance = attendanceService.getAttendance(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("attendance", theAttendance);

		return "attendance-reset-form";

	}

	@PostMapping("/resetSuAttendance")
	public String resetSuAttendance(@RequestParam("id") int theId,
			@RequestParam("supervisorComment") String supervisorComment, String supervisorCommentDate,

			Model theModel) {
		attendanceService.resetSuAttendance(theId, supervisorComment, supervisorCommentDate);

		return "redirect:/attendanceSupervisor/list_attendance_su";
	}

	@GetMapping("/list_dg_su")
	public String listSuDg(Model theModel) {
		
		List<String> theSchools = attendanceService.getSchools();
		List<String> theClasses = attendanceService.getClasses();
		List<String> theRegions = attendanceService.getRegions();

		List<Attendance> theAttendance = attendanceService.getSuDg();
		
		theModel.addAttribute("schools", theSchools);
		theModel.addAttribute("classes", theClasses);
		theModel.addAttribute("regions", theRegions);
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_dg_su";
	}

	@GetMapping("/searchSuDg")
	public String searchSuDg(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("studentsubject") String studentsubject,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchSuAttendance(studentschool, studentregion, studentform,
				fromDate, toDate, studentsubject);

		// add the customers to the model
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("fromDate", fromDate);
		theModel.addAttribute("toDate", toDate);
		theModel.addAttribute("studentform", studentform);
		theModel.addAttribute("studentregion", studentregion);
		theModel.addAttribute("studentschool", studentschool);
		theModel.addAttribute("studentsubject", studentsubject);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_dg_suFiltered";
	}

	@GetMapping("/list_attendance_su_approved")
	public String listSuAttendanceApproved(Model theModel) {

		List<Attendance> theAttendance = attendanceService.getSuAttendanceApproved();
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_su_approved";
	}

	@GetMapping("/searchApprovedAttendance")
	public String searchApprovedAttendance(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate,

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchApprovedAttendance(studentschool, studentregion,
				studentform, fromDate, toDate);

		// add the customers to the model
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("fromDate", fromDate);
		theModel.addAttribute("toDate", toDate);
		theModel.addAttribute("studentform", studentform);
		theModel.addAttribute("studentregion", studentregion);
		theModel.addAttribute("studentschool", studentschool);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_su_approved_filtered";
	}

	@GetMapping("/approvedAttendanceToExcel")
	public ModelAndView getExcel(HttpServletRequest request) {

		// ModelAndView modelAndView;
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String studentform = request.getParameter("studentform");
		String studentregion = request.getParameter("studentregion");
		String studentschool = request.getParameter("studentschool");
		String excel = request.getParameter("excel");
		String pdf = request.getParameter("pdf");
	

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchApprovedAttendance(studentschool, studentregion,
				studentform, fromDate, toDate);
		
		if(excel != null) {
			
			return new ModelAndView(new AttendanceExcelReportView(), "attendanceList", theAttendance);
		} else {
			return new ModelAndView(new AttendancePdfReportView(), "attendanceList", theAttendance);
		}

	}

	@GetMapping("/list_attendance_su_verified")
	public String listSuAttendanceVerified(Model theModel) {

		List<Attendance> theAttendance = attendanceService.getSuAttendanceVerified();
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_su_verified";
	}
	
	@GetMapping("/userManual")
	public String userManual() {

		

		return "admin_user_manual";
	}
	
	

}
