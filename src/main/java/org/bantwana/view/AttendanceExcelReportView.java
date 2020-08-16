package org.bantwana.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bantwana.entity.Attendance;
import org.springframework.web.servlet.view.document.AbstractXlsView;

public class AttendanceExcelReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment;filename=\"attendance.xls\"");
		List<Attendance> attendanceList = (List<Attendance>) model.get("attendanceList");
		
		
		Sheet sheet = workbook.createSheet("Student Data");
		Row header = sheet.createRow(0);
		
		
		header.createCell(0).setCellValue("Student ID");
		header.createCell(1).setCellValue("Student Pin");
		header.createCell(2).setCellValue("Student Name");
		header.createCell(3).setCellValue("Gender");
		header.createCell(4).setCellValue("Date of Birth");
		header.createCell(5).setCellValue("School");
		header.createCell(5).setCellValue("Class");
		header.createCell(6).setCellValue("Subject");
		header.createCell(7).setCellValue("Attendance");
		
		

		int rowNum = 1;
		for (Attendance entry : attendanceList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(entry.getDeviceActionformattedDate());
			row.createCell(1).setCellValue(entry.getStudentpin());
			row.createCell(2).setCellValue(entry.getStudentfirstname() + " " + entry.getStudentsurname());
			row.createCell(3).setCellValue(entry.getStudentgender());
			row.createCell(4).setCellValue(entry.getStudentschool());
			row.createCell(5).setCellValue(entry.getStudentform());
			row.createCell(6).setCellValue(entry.getStudentsubject());
			row.createCell(7).setCellValue(entry.getStudentattendance());
		}
	}

}
