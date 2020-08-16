package org.bantwana.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bantwana.entity.Attendance;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class AttendancePdfReportView extends AbstractPdfView {

	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Attendance> attendanceList = (List<Attendance>) model.get("attendanceList");

		Table table = new Table(9);
		table.setTableFitsPage(true);

		table.addCell("Session Date");
		table.addCell("Student Pin");
		table.addCell("Student Name");
		table.addCell("Gender");
		table.addCell("Date of Birth");
		table.addCell("School");
		table.addCell("Class");
		table.addCell("Subject");
		table.addCell("Attendance");

		for (Attendance entry : attendanceList) {
			
			table.addCell(entry.getDeviceActionformattedDate());
			table.addCell(entry.getStudentpin());
			table.addCell(entry.getStudentfirstname() + " " + entry.getStudentsurname());
			table.addCell(entry.getStudentgender());
			table.addCell(entry.getStudentdateofbirth());
			table.addCell(entry.getStudentschool());
			table.addCell(entry.getStudentform());
			table.addCell(entry.getStudentsubject());
			table.addCell(entry.getStudentattendance());

		}
		document.add(table);
	}

}
