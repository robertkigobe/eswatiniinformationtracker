package org.bantwana.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bantwana.entity.Attendance;
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
@RequestMapping("/attendanceMe")
public class AttendanceControllerMe {

	// need to inject our customer service
	@Autowired
	private AttendanceService attendanceService;

	// monitoring actions

	@GetMapping("/list_attendance_me")
	public String listMeAttendance(Model theModel) {

		List<String> theSchools = attendanceService.getSchools();
		List<String> theSubjects = attendanceService.getSubjects();
		List<String> theClasses = attendanceService.getClasses();
		List<String> theRegions = attendanceService.getRegions();

		List<Attendance> theAttendance = attendanceService.getMeAttendance();
		theModel.addAttribute("schools", theSchools);
		theModel.addAttribute("subjects", theSubjects);
		theModel.addAttribute("classes", theClasses);
		theModel.addAttribute("regions", theRegions);
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_attendance_me";
	}

	@GetMapping("/searchMeAttendance")
	public String searchMeAttendance(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("studentsubject") String studentsubject,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchMeAttendance(studentschool, studentregion, studentform,
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

		return "list_attendance_meFiltered";
	}

	@GetMapping("/searchMeAttendanceVerifyAll")
	public String searchMeAttendanceVerifyAll(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("studentsubject") String studentsubject,

			Model theModel) {

		// delete the customer
		attendanceService.verifyAllMe(studentschool, studentregion, studentform, fromDate, toDate, studentsubject);

		return "redirect:/attendanceMe/list_attendance_me";
	}

	@GetMapping("/showFormForPackAttendance")
	public String showFormForPackAttendance(@RequestParam("attendanceId") int theId, Model theModel) {

		// get the customer from our service
		Attendance theAttendance = attendanceService.getAttendance(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("attendance", theAttendance);

		return "attendance-pack-form";

	}

	@PostMapping("/pendMeAttendance")
	public String pendMeAttendance(@ModelAttribute("attendance") Attendance theAttendance) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		theAttendance.setMeCommentDate(dtf.format(now));

		attendanceService.pendAttendanceMe(theAttendance);

		return "redirect:/attendanceMe/list_attendance_me";
	}

	@GetMapping("/list_pending_attendance_me")
	public String listMePendingAttendance(Model theModel) {

		List<Attendance> theAttendance = attendanceService.getMePendingAttendance();
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_pending_attendance_me";
	}

	@GetMapping("/list_reset_attendance_me")
	public String listMeRestAttendance(Model theModel) {

		List<Attendance> theAttendance = attendanceService.getResetMeAttendance();
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_reset_attendance_me";
	}

	@GetMapping("/list_dg_me")
	public String listMeDg(Model theModel) {

		List<String> theSchools = attendanceService.getSchools();
		List<String> theClasses = attendanceService.getClasses();
		List<String> theRegions = attendanceService.getRegions();

		List<Attendance> theAttendance = attendanceService.getMeDg();

		theModel.addAttribute("schools", theSchools);
		theModel.addAttribute("classes", theClasses);
		theModel.addAttribute("regions", theRegions);
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_dg_me";
	}

	@GetMapping("/searchMeDg")
	public String searchMeDg(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("studentsubject") String studentsubject,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,

			Model theModel) {

		// search customers from the service
		List<Attendance> theAttendance = attendanceService.searchMeAttendance(studentschool, studentregion, studentform,
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

		return "list_dg_meFiltered";
	}

	@GetMapping("/dgMeVerifyAll")
	public String dgMeVerifyAll(

			@RequestParam("studentschool") String studentschool, @RequestParam("studentregion") String studentregion,
			@RequestParam("studentform") String studentform, @RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate, @RequestParam("studentsubject") String studentsubject,

			Model theModel) {

		// delete the customer
		attendanceService.verifyAllMe(studentschool, studentregion, studentform, fromDate, toDate, studentsubject);

		return "redirect:/attendanceMe/list_dg_me";
	}

	@GetMapping("/list_pending_dg_me")
	public String listMePendingDg(Model theModel) {

		List<Attendance> theAttendance = attendanceService.getMePendingDg();
		theModel.addAttribute("attendance", theAttendance);
		theModel.addAttribute("attendanceCount", theAttendance.size());

		return "list_pending_dg_me";
	}

	@GetMapping("/resetPendingMeAttendance")
	public String resetPendingMeAttendance(@RequestParam("attendanceId") int theId) {

		// update the customer
		attendanceService.resetAdminAttendance(theId);
		return "redirect:/attendanceMe/list_pending_attendance_me";
	}

	@GetMapping("/resetPendingMeDg")
	public String resetPendingMeDg(@RequestParam("attendanceId") int theId) {

		// update the customer
		attendanceService.resetAdminAttendance(theId);
		return "redirect:/attendanceMe/list_pending_dg_me";
	}

	@GetMapping("/verifyPendingMeAttendance")
	public String verifyPendingMeAttendance(@RequestParam("attendanceId") int theId) {

		// update the customer
		attendanceService.verifyAdminAttendance(theId);
		return "redirect:/attendanceMe/list_pending_attendance_me";
	}

}
