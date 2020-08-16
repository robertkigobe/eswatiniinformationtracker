package org.bantwana.dao;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.bantwana.entity.Attendance;
import org.bantwana.entity.AttendanceStatistics;
import org.bantwana.entity.AttendanceSubmissionStatistics;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	String studentsubject2 = null;

	//// admin

	@Override
	public List<Attendance> getAdminAttendance() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and studentform != 'Form' order by dateOfImport desc, studentschool, studentform,studentsubject",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public void deleteAdminAttendance(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery("delete from Attendance  where id = :attendanceId");
		theQuery.setParameter("attendanceId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public void resetAdminAttendance(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery("update Attendance set flag = 'New' where id = :attendanceId");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("attendanceId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public void verifyAdminAttendance(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery("update Attendance set flag = 'Verified' where id = :attendanceId");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("attendanceId", theId);

		theQuery.executeUpdate();
	}

	@Override
	public List<Attendance> getDg() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery("from Attendance where studentsubject = 'DG Packs' ",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	@Override
	public List<Attendance> searchAdminAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Attendance> theQuery = null;

		if (studentschool != null && studentregion != null && studentform != null && fromDate != null && toDate != null
				&& studentsubject != null) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Attendance where lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate and lower(studentsubject) = :theStudentsubject",
					Attendance.class);
			theQuery.setParameter("theStudentschool", studentschool);
			theQuery.setParameter("theStudentregion", studentregion);
			theQuery.setParameter("theStudentform", studentform + "%");
			theQuery.setParameter("theFromDate", fromDate);
			theQuery.setParameter("theToDate", toDate);
			theQuery.setParameter("theStudentsubject", studentsubject);

			// System.out.println("Formatted Date is: " + startDate.toString());

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Attendance where studentsubject != 'DG Packs' ",
					Attendance.class);

		}

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public void resetAllAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery(
				"update Attendance set flag = 'New' where flag != 'Approved' and  studentsubject = :theStudentsubject and lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("theStudentschool", studentschool);
		theQuery.setParameter("theStudentregion", studentregion);
		theQuery.setParameter("theStudentform", studentform + "%");
		theQuery.setParameter("theFromDate", fromDate);
		theQuery.setParameter("theToDate", toDate);
		theQuery.setParameter("theStudentsubject", studentsubject);

		theQuery.executeUpdate();

	}

	@Override
	public void deleteAllAttendance(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery(
				"delete from Attendance where flag != 'Approved' studentsubject = :theStudentsubject and lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("theStudentschool", studentschool);
		theQuery.setParameter("theStudentregion", studentregion);
		theQuery.setParameter("theStudentform", studentform + "%");
		theQuery.setParameter("theFromDate", fromDate);
		theQuery.setParameter("theToDate", toDate);
		theQuery.setParameter("theStudentsubject", studentsubject);

		theQuery.executeUpdate();

	}

	@Override
	public void deleteAllAttendance(Integer fromRange, Integer toRange) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession
				.createQuery("delete from Attendance where flag != 'Approved' and id between :theFromId and :theToId ");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("theFromId", fromRange);
		theQuery.setParameter("theToId", toRange);

		theQuery.executeUpdate();

	}

	// ME actions

	@Override
	public List<Attendance> getMeAttendance() {

		List<Attendance> attendance = new ArrayList<Attendance>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery("SELECT `attendance`.`id`,    "
				+ "IFNULL(`attendance`.`deviceActionformattedDate`, 0)  deviceActionformattedDate, "
				+ "IFNULL(`attendance`.`deviceActionandroid_id`,     0)  deviceActionandroid_id, "
				+ "IFNULL(`attendance`.`deviceActionusername`,    0)  deviceActionusername, "
				+ "IFNULL(`attendance`.`deviceActionuserrole`,    0)  deviceActionuserrole, "
				+ "IFNULL(`attendance`.`deviceActionlatValue`,     0)  deviceActionlatValue, "
				+ "IFNULL(`attendance`.`deviceActionlngValue`,     0)  deviceActionlngValue, "
				+ "IFNULL(`attendance`.`deviceActionactions`,     0)  deviceActionactions, "
				+ "IFNULL(`attendance`.`teacherusername1t`,      0)  teacherusername1t, "
				+ "IFNULL(`attendance`.`teacherpint`,     0)  teacherpint, "
				+ "IFNULL(`attendance`.`teacherfirstnamet`,     0)  teacherfirstnamet, "
				+ "IFNULL(`attendance`.`teachersecondnamet`,     0)  teachersecondnamet, "
				+ "IFNULL(`attendance`.`teachersurnamet`,     0)  teachersurnamet, "
				+ "IFNULL(`attendance`.`teacherschoolt`,     0)  teacherschoolt, "
				+ "IFNULL(`attendance`.`dateOfAttendance`,0)  dateOfAttendance, "
				+ "IFNULL(`attendance`.`dateOfImport`,0)  dateOfImport, "
				+ "IFNULL(`attendance`.`studentpin`,0)  studentpin, "
				+ "IFNULL(`attendance`.`studentfirstname`,0)  studentfirstname, "
				+ "IFNULL(`attendance`.`studentregion`,0)  studentregion, "
				+ "IFNULL(`attendance`.`studentsurname`,0)  studentsurname, "
				+ "IFNULL(`attendance`.`studentschool`,0)  studentschool, "
				+ "IFNULL(`attendance`.`studentgender`, 0)  studentgender, "
				+ "IFNULL(`attendance`.`studentdateofbirth`,  0)  studentdateofbirth, "
				+ "IFNULL(`attendance`.`studentform`,  0)  studentform, "
				+ "IFNULL(`attendance`.`studentstatus`, 0)  studentstatus, " + "case (`attendance`.`studentsubject`)  "
				+ "when 'Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)' then 'Form 1 - IGC'	"
				+ "when 'Form One: Test' then 'Form 1 - Test'	"
				+ "when 'Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)' then 'Form 1 - GUYNS'	"
				+ "when 'Form One: Health Promotion - Reporting Sexual Abuse (RSA)' then 'Form One:RSA'"
				+ "when 'Form One: Guidance and Counselling - Career Awareness (CA)' then 'Form 1 - CA'     "
				+ "when 'Form One: Guidance and Counselling - Developing Self-Awareness (DSA)' then 'Form 1 - DSA'     "
				+ "when 'Form One: Guidance and Counselling - Peer Pressure (PP)' then 'Form 1 - PP'    "
				+ "when 'Form One: Guidance and Counselling - Goal Setting (GS)' then 'Form 1 - GS'     "
				+ "when 'Form One: Guidance and Counselling - Decision Making (DM)' then 'Form 1 - DM'    "
				+ "when 'Form One: Guidance and Counselling - Time Management  (TMGT)' then 'Form 1 - TMGT'    "
				+ "when 'Form One: Guidance and Counselling - Study Skills (SS)' then 'Form 1 - SS'     "
				+ "when 'Form One: Guidance and Counselling - Societal Norms and Values (SNV)' then 'Form 1 - SNV'    "
				+ "when 'Form One: Guidance and Counselling - Dealing With Loss (DWL)' then 'Form 1 - DWL'    "
				+ "when 'Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)' then 'Form 1 - PBCAP'     "
				+ "when 'Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)' then 'Form 1 - SECP'     "
				+ "when 'Form One: Health Promotion - Sexual Abuse (SA)' then 'Form 1 - SA'     "
				+ "when 'Form OnHealth Promotion - Reporting Sexual Abuse (RSA)' then 'Form 1 - RSA'     "
				+ "when 'Form One: HIV and AIDS -  (HIVA)' then 'Form 1 - HIVA'     "
				+ "when 'Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)' then 'Form 1 - DHS'     "
				+ "when 'Form One: HIV and AIDS - Prevention of HIV Infections (PHI)' then 'Form 1 - PHI'    "
				+ "when 'Form One: HIV and AIDS - Health Seeking Behaviours (HSB)' then 'Form 1 - HSB'     "
				+ "when 'Form One: HIV and AIDS - Positive Living (PL)' then 'Form 1 - PL'     "
				+ "when 'Form Two: Guidance and Counselling - Communication Skills (CS)' then 'Form 2 - CS'     "
				+ "when 'Form Two: Guidance and Counselling - Assertiveness (ASS)' then 'Form 2 - ASS'     "
				+ "when 'Form Two: Guidance and Counselling - Effective Study Skills (ESS)' then 'Form 2 - ESS'     "
				+ "when 'Form Two: Guidance and Counselling - Time Management (TMGT)' then 'Form 2 - TMGT'     "
				+ "when 'Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)' then 'Form 2 - SFA'     "
				+ "when 'Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)' then 'Form 2 - PSHI'     "
				+ "when 'Form Two: Health Promotion - Immune Response to HIV (IRH)' then 'Form 2 - IRH'     "
				+ "when 'Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)' then 'Form 2 - SSSTI'     "
				+ "when 'Form Two: Health Promotion - Intergenerational Sex (IGS)' then 'Form 2 - IGS'     "
				+ "when 'Form Two: Health Promotion - Transactional Sex (TAS)' then 'Form 2 - TAS'     "
				+ "when 'Form Two: Health Promotion - Delaying Sexual Debut (DSD)' then 'Form 2 - DSD'     "
				+ "when 'Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)' then 'Form 2 - ENC'     "
				+ "when 'Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)' then 'Form 2 - HID'     "
				+ "when 'Form Two: HIV and AIDS - Early Parenthood (EPHD)' then 'Form 2 - EPHD'     "
				+ "when 'Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)' then 'Form 2 - CASA'     "
				+ "when 'Form Three: Guidance and Counselling - Transition Learning (TL)' then 'Form 3 - TL'     "
				+ "when 'Form Three: Guidance and Counselling - Career Life (CL)' then 'Form 3 - CL'     "
				+ "when 'Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)' then 'Form 3 - JSS'     "
				+ "when 'Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)' then 'Form 3 - ES'     "
				+ "when 'Form Three: Guidance and Counselling - The Work Place (TWP)' then 'Form 3 - TWP'    "
				+ "when 'Form Three: Guidance and Counselling - Self Awareness (SA)' then 'Form 3 - SA'     "
				+ "when 'Form Three: Guidance and Counselling - Dealing with Emotions (DWE)' then 'Form 3 - DWE'    "
				+ "when 'Form Three: Guidance and Counselling - Time Management (TMGT)' then 'Form 3 - TMGT'     "
				+ "when 'Form Three: Guidance and Counselling - Study Skills (SS)' then 'Form 3 - SS'     "
				+ "when 'Form Three: Guidance and Counselling - Career Awareness (CA)' then 'Form 3 - CA'     "
				+ "when 'Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)' then 'Form 3 - ASADH'     "
				+ "when 'Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)' then 'Form 3 - MCP'     "
				+ "when 'Form Three: Health Promotion - International Sex  (IS)' then 'Form 3 - IS'     "
				+ "when 'Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)' then 'Form 3 - HSB'     "
				+ "when 'Form Three: HIV and AIDS - Nutrition and HIV (NH)' then 'Form 3 - NH'     "
				+ "when 'Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)' then 'Form 4 - TSSL'     "
				+ "when 'Form Four: Guidance and Counselling - Self-Concept (SC)' then 'Form 4 - SC'     "
				+ "when 'Form Four: Guidance and Counselling - Decision Making (DM) ' then 'Form 4 - DM'     "
				+ "when 'Form Four: Guidance and Counselling - Critical Thinking (CT)' then 'Form 4 - CT'     "
				+ "when 'Form Four: Guidance and Counselling - Goal Setting (GS)' then 'Form 4 - GS'     "
				+ "when 'Form Four: Health Promotion - Antiretroviral Treatment (ART)' then 'Form 4 - ART'    "
				+ "when 'Form Four: Health Promotion - Delaying Sexual Debut (DSD)' then 'Form 4 - DSD'     "
				+ "when 'Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)' then 'Form 4 - MCP'    "
				+ "when 'Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)' then 'Form 4 - RSB'     "
				+ "when 'Form Four: HIV and AIDS - Opportunistic Infections (OPI)' then 'Form 4 - OPI'     "
				+ "when 'Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)' then 'Form 4 - EISO'    "
				+ " when 'Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)' then 'Form 4 - CSA'     "
				+ "when 'Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)' then 'Form 5 - PTHEPW'     "
				+ "when 'Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)' then 'Form 5 - HETI'     "
				+ "when 'Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)' then 'Form 5 - S/B'     "
				+ "when 'Form Five: Guidance and Counselling - Work Ethics  (WE)' then 'Form 5 - WE'     "
				+ "when 'Money Management Skills (MMS)' then 'Form 5 - MMS'     "
				+ "when 'Form Five: Guidance and Counselling - Labour Laws (LL)' then 'Form 5 - LL'     "
				+ "when 'Form Five: Health Promotion - HIV Testing and Counselling  (HTC)' then 'Form 5 - HTC'     "
				+ "when 'Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)' then 'Form 5 - CADS'     "
				+ "when 'Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)' then 'Form 5 - CNCD'	"
				+ "when 'Form Five: Guidance and Counselling - Money Management Skills (MMS)' then 'Form 5 - MMS'     "
				+ "when 'Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)' then 'Form 1 - IGC'     "
				+ "else `attendance`.`studentsubject` end as `studentsubject` ,    "
				+ "IFNULL(`attendance`.`studentattendance`,      0)  studentattendance,"
				+ "IFNULL(`attendance`.`flag`,  0)  flag," + "IFNULL(`attendance`.`meComment`,  0)  meComment,"
				+ "IFNULL(`attendance`.`meOfficer`,  0)  meOfficer,"
				+ "IFNULL(`attendance`.`supervisor`,  0)  supervisor," + "IFNULL(`attendance`.`admin`,  0)  admin,"
				+ "IFNULL(`attendance`.`adminComment`,  0)  adminComment,"
				+ "IFNULL(`attendance`.`supervisorComment`,   0)  supervisorComment,"
				+ "IFNULL(`attendance`.`adminCommentDate`,  0)  adminCommentDate,"
				+ "IFNULL(`attendance`.`supervisorCommentDate`,  0)  supervisorCommentDate,"
				+ "IFNULL(`attendance`.`meCommentDate`, 0)  meCommentDate "
				+ "FROM `bantwanaclasstracker`.`attendance` "
				+ "where studentsubject != 'DG Packs' and studentform != 'Form' and flag = 'New' order by dateOfImport desc;");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {

			Attendance stat = new Attendance();

			stat.setId((int) (row[0]));
			stat.setDeviceActionformattedDate(row[1].toString());
			stat.setDeviceActionandroid_id(row[2].toString());
			stat.setDeviceActionusername(row[3].toString());
			stat.setDeviceActionuserrole(row[4].toString());
			stat.setDeviceActionlatValue(row[5].toString());
			stat.setDeviceActionlngValue(row[6].toString());
			stat.setDeviceActionactions(row[7].toString());
			stat.setTeacherusername1t(row[8].toString());
			stat.setTeacherpint(row[9].toString());
			stat.setTeacherfirstnamet(row[10].toString());
			stat.setTeachersecondnamet(row[11].toString());
			stat.setTeachersurnamet(row[12].toString());
			stat.setTeacherschoolt(row[13].toString());
			stat.setDateOfAttendance(row[14].toString());
			stat.setDateOfImport(row[15].toString());
			stat.setStudentpin(row[16].toString());
			stat.setStudentfirstname(row[17].toString());
			stat.setStudentregion(row[18].toString());
			stat.setStudentsurname(row[19].toString());
			stat.setStudentschool(row[20].toString());
			stat.setStudentgender(row[21].toString());
			stat.setStudentdateofbirth(row[22].toString());
			stat.setStudentform(row[23].toString());
			stat.setStudentstatus(row[24].toString());
			stat.setStudentsubject(row[25].toString());
			stat.setStudentattendance(row[26].toString());
			stat.setFlag(row[27].toString());
			stat.setMeComment(row[28].toString());
			stat.setMeOfficer(row[29].toString());
			stat.setSupervisor(row[30].toString());
			stat.setAdmin(row[31].toString());
			stat.setAdminComment(row[32].toString());
			stat.setSupervisorComment(row[33].toString());
			stat.setAdminCommentDate(row[34].toString());
			stat.setSupervisorCommentDate(row[35].toString());
			stat.setMeCommentDate(row[36].toString());

			attendance.add(new Attendance(

					stat.getId(), stat.getDeviceActionformattedDate().toString(),
					stat.getDeviceActionandroid_id().toString(), stat.getDeviceActionusername().toString(),
					stat.getDeviceActionuserrole().toString(), stat.getDeviceActionlatValue().toString(),
					stat.getDeviceActionlngValue().toString(), stat.getDeviceActionactions().toString(),
					stat.getTeacherusername1t().toString(), stat.getTeacherpint().toString(),
					stat.getTeacherfirstnamet().toString(), stat.getTeachersecondnamet().toString(),
					stat.getTeachersurnamet().toString(), stat.getTeacherschoolt().toString(),
					stat.getDateOfAttendance().toString(), stat.getDateOfImport().toString(),
					stat.getStudentpin().toString(), stat.getStudentfirstname().toString(),
					stat.getStudentregion().toString(), stat.getStudentsurname().toString(),
					stat.getStudentschool().toString(), stat.getStudentgender().toString(),
					stat.getStudentdateofbirth().toString(), stat.getStudentform().toString(),
					stat.getStudentstatus().toString(), stat.getStudentsubject().toString(),
					stat.getStudentattendance().toString(), stat.getFlag().toString(),
					stat.getMeCommentDate().toString(), stat.getMeOfficer().toString(), stat.getSupervisor().toString(),
					stat.getAdmin().toString(), stat.getAdminComment().toString(),
					stat.getSupervisorComment().toString(), stat.getAdminCommentDate().toString(),
					stat.getSupervisorCommentDate().toString(), stat.getMeCommentDate().toString()

			));
		}

		// return the results
		return attendance;
	}

	@Override
	public void verifyAllMe(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery(
				"update Attendance set flag = 'Verified' where studentsubject = :theStudentsubject and lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("theStudentschool", studentschool);
		theQuery.setParameter("theStudentregion", studentregion);
		theQuery.setParameter("theStudentform", studentform + "%");
		theQuery.setParameter("theFromDate", fromDate);
		theQuery.setParameter("theToDate", toDate);
		theQuery.setParameter("theStudentsubject", studentsubject);

		theQuery.executeUpdate();

	}

	@Override
	public List<Attendance> searchMeAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject) {

		List<Attendance> attendance = new ArrayList<Attendance>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		if (studentschool != null && studentregion != null && studentform != null && fromDate != null
				&& toDate != null) {

			// create a query ... sort by last name
			SQLQuery theQuery = currentSession.createSQLQuery("SELECT `attendance`.`id`,    "
					+ "IFNULL(`attendance`.`deviceActionformattedDate`, 0)  deviceActionformattedDate, "
					+ "IFNULL(`attendance`.`deviceActionandroid_id`,     0)  deviceActionandroid_id, "
					+ "IFNULL(`attendance`.`deviceActionusername`,    0)  deviceActionusername, "
					+ "IFNULL(`attendance`.`deviceActionuserrole`,    0)  deviceActionuserrole, "
					+ "IFNULL(`attendance`.`deviceActionlatValue`,     0)  deviceActionlatValue, "
					+ "IFNULL(`attendance`.`deviceActionlngValue`,     0)  deviceActionlngValue, "
					+ "IFNULL(`attendance`.`deviceActionactions`,     0)  deviceActionactions, "
					+ "IFNULL(`attendance`.`teacherusername1t`,      0)  teacherusername1t, "
					+ "IFNULL(`attendance`.`teacherpint`,     0)  teacherpint, "
					+ "IFNULL(`attendance`.`teacherfirstnamet`,     0)  teacherfirstnamet, "
					+ "IFNULL(`attendance`.`teachersecondnamet`,     0)  teachersecondnamet, "
					+ "IFNULL(`attendance`.`teachersurnamet`,     0)  teachersurnamet, "
					+ "IFNULL(`attendance`.`teacherschoolt`,     0)  teacherschoolt, "
					+ "IFNULL(`attendance`.`dateOfAttendance`,0)  dateOfAttendance, "
					+ "IFNULL(`attendance`.`dateOfImport`,0)  dateOfImport, "
					+ "IFNULL(`attendance`.`studentpin`,0)  studentpin, "
					+ "IFNULL(`attendance`.`studentfirstname`,0)  studentfirstname, "
					+ "IFNULL(`attendance`.`studentregion`,0)  studentregion, "
					+ "IFNULL(`attendance`.`studentsurname`,0)  studentsurname, "
					+ "IFNULL(`attendance`.`studentschool`,0)  studentschool, "
					+ "IFNULL(`attendance`.`studentgender`, 0)  studentgender, "
					+ "IFNULL(`attendance`.`studentdateofbirth`,  0)  studentdateofbirth, "
					+ "IFNULL(`attendance`.`studentform`,  0)  studentform, "
					+ "IFNULL(`attendance`.`studentstatus`, 0)  studentstatus, "
					+ "case (`attendance`.`studentsubject`)  "
					+ "when 'Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)' then 'Form 1 - IGC'	"
					+ "when 'Form One: Test' then 'Form 1 - Test'	"
					+ "when 'Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)' then 'Form 1 - GUYNS'	"
					+ "when 'Form One: Health Promotion - Reporting Sexual Abuse (RSA)' then 'Form One:RSA'"
					+ "when 'Form One: Guidance and Counselling - Career Awareness (CA)' then 'Form 1 - CA'     "
					+ "when 'Form One: Guidance and Counselling - Developing Self-Awareness (DSA)' then 'Form 1 - DSA'     "
					+ "when 'Form One: Guidance and Counselling - Peer Pressure (PP)' then 'Form 1 - PP'    "
					+ "when 'Form One: Guidance and Counselling - Goal Setting (GS)' then 'Form 1 - GS'     "
					+ "when 'Form One: Guidance and Counselling - Decision Making (DM)' then 'Form 1 - DM'    "
					+ "when 'Form One: Guidance and Counselling - Time Management  (TMGT)' then 'Form 1 - TMGT'    "
					+ "when 'Form One: Guidance and Counselling - Study Skills (SS)' then 'Form 1 - SS'     "
					+ "when 'Form One: Guidance and Counselling - Societal Norms and Values (SNV)' then 'Form 1 - SNV'    "
					+ "when 'Form One: Guidance and Counselling - Dealing With Loss (DWL)' then 'Form 1 - DWL'    "
					+ "when 'Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)' then 'Form 1 - PBCAP'     "
					+ "when 'Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)' then 'Form 1 - SECP'     "
					+ "when 'Form One: Health Promotion - Sexual Abuse (SA)' then 'Form 1 - SA'     "
					+ "when 'Form OnHealth Promotion - Reporting Sexual Abuse (RSA)' then 'Form 1 - RSA'     "
					+ "when 'Form One: HIV and AIDS -  (HIVA)' then 'Form 1 - HIVA'     "
					+ "when 'Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)' then 'Form 1 - DHS'     "
					+ "when 'Form One: HIV and AIDS - Prevention of HIV Infections (PHI)' then 'Form 1 - PHI'    "
					+ "when 'Form One: HIV and AIDS - Health Seeking Behaviours (HSB)' then 'Form 1 - HSB'     "
					+ "when 'Form One: HIV and AIDS - Positive Living (PL)' then 'Form 1 - PL'     "
					+ "when 'Form Two: Guidance and Counselling - Communication Skills (CS)' then 'Form 2 - CS'     "
					+ "when 'Form Two: Guidance and Counselling - Assertiveness (ASS)' then 'Form 2 - ASS'     "
					+ "when 'Form Two: Guidance and Counselling - Effective Study Skills (ESS)' then 'Form 2 - ESS'     "
					+ "when 'Form Two: Guidance and Counselling - Time Management (TMGT)' then 'Form 2 - TMGT'     "
					+ "when 'Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)' then 'Form 2 - SFA'     "
					+ "when 'Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)' then 'Form 2 - PSHI'     "
					+ "when 'Form Two: Health Promotion - Immune Response to HIV (IRH)' then 'Form 2 - IRH'     "
					+ "when 'Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)' then 'Form 2 - SSSTI'     "
					+ "when 'Form Two: Health Promotion - Intergenerational Sex (IGS)' then 'Form 2 - IGS'     "
					+ "when 'Form Two: Health Promotion - Transactional Sex (TAS)' then 'Form 2 - TAS'     "
					+ "when 'Form Two: Health Promotion - Delaying Sexual Debut (DSD)' then 'Form 2 - DSD'     "
					+ "when 'Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)' then 'Form 2 - ENC'     "
					+ "when 'Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)' then 'Form 2 - HID'     "
					+ "when 'Form Two: HIV and AIDS - Early Parenthood (EPHD)' then 'Form 2 - EPHD'     "
					+ "when 'Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)' then 'Form 2 - CASA'     "
					+ "when 'Form Three: Guidance and Counselling - Transition Learning (TL)' then 'Form 3 - TL'     "
					+ "when 'Form Three: Guidance and Counselling - Career Life (CL)' then 'Form 3 - CL'     "
					+ "when 'Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)' then 'Form 3 - JSS'     "
					+ "when 'Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)' then 'Form 3 - ES'     "
					+ "when 'Form Three: Guidance and Counselling - The Work Place (TWP)' then 'Form 3 - TWP'    "
					+ "when 'Form Three: Guidance and Counselling - Self Awareness (SA)' then 'Form 3 - SA'     "
					+ "when 'Form Three: Guidance and Counselling - Dealing with Emotions (DWE)' then 'Form 3 - DWE'    "
					+ "when 'Form Three: Guidance and Counselling - Time Management (TMGT)' then 'Form 3 - TMGT'     "
					+ "when 'Form Three: Guidance and Counselling - Study Skills (SS)' then 'Form 3 - SS'     "
					+ "when 'Form Three: Guidance and Counselling - Career Awareness (CA)' then 'Form 3 - CA'     "
					+ "when 'Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)' then 'Form 3 - ASADH'     "
					+ "when 'Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)' then 'Form 3 - MCP'     "
					+ "when 'Form Three: Health Promotion - International Sex  (IS)' then 'Form 3 - IS'     "
					+ "when 'Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)' then 'Form 3 - HSB'     "
					+ "when 'Form Three: HIV and AIDS - Nutrition and HIV (NH)' then 'Form 3 - NH'     "
					+ "when 'Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)' then 'Form 4 - TSSL'     "
					+ "when 'Form Four: Guidance and Counselling - Self-Concept (SC)' then 'Form 4 - SC'     "
					+ "when 'Form Four: Guidance and Counselling - Decision Making (DM) ' then 'Form 4 - DM'     "
					+ "when 'Form Four: Guidance and Counselling - Critical Thinking (CT)' then 'Form 4 - CT'     "
					+ "when 'Form Four: Guidance and Counselling - Goal Setting (GS)' then 'Form 4 - GS'     "
					+ "when 'Form Four: Health Promotion - Antiretroviral Treatment (ART)' then 'Form 4 - ART'    "
					+ "when 'Form Four: Health Promotion - Delaying Sexual Debut (DSD)' then 'Form 4 - DSD'     "
					+ "when 'Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)' then 'Form 4 - MCP'    "
					+ "when 'Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)' then 'Form 4 - RSB'     "
					+ "when 'Form Four: HIV and AIDS - Opportunistic Infections (OPI)' then 'Form 4 - OPI'     "
					+ "when 'Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)' then 'Form 4 - EISO'    "
					+ " when 'Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)' then 'Form 4 - CSA'     "
					+ "when 'Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)' then 'Form 5 - PTHEPW'     "
					+ "when 'Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)' then 'Form 5 - HETI'     "
					+ "when 'Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)' then 'Form 5 - S/B'     "
					+ "when 'Form Five: Guidance and Counselling - Work Ethics  (WE)' then 'Form 5 - WE'     "
					+ "when 'Money Management Skills (MMS)' then 'Form 5 - MMS'     "
					+ "when 'Form Five: Guidance and Counselling - Labour Laws (LL)' then 'Form 5 - LL'     "
					+ "when 'Form Five: Health Promotion - HIV Testing and Counselling  (HTC)' then 'Form 5 - HTC'     "
					+ "when 'Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)' then 'Form 5 - CADS'     "
					+ "when 'Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)' then 'Form 5 - CNCD'	"
					+ "when 'Form Five: Guidance and Counselling - Money Management Skills (MMS)' then 'Form 5 - MMS'     "
					+ "when 'Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)' then 'Form 1 - IGC'     "
					+ "else `attendance`.`studentsubject` end as `studentsubject` ,    "
					+ "IFNULL(`attendance`.`studentattendance`,      0)  studentattendance,"
					+ "IFNULL(`attendance`.`flag`,  0)  flag," + "IFNULL(`attendance`.`meComment`,  0)  meComment,"
					+ "IFNULL(`attendance`.`meOfficer`,  0)  meOfficer,"
					+ "IFNULL(`attendance`.`supervisor`,  0)  supervisor," + "IFNULL(`attendance`.`admin`,  0)  admin,"
					+ "IFNULL(`attendance`.`adminComment`,  0)  adminComment,"
					+ "IFNULL(`attendance`.`supervisorComment`,   0)  supervisorComment,"
					+ "IFNULL(`attendance`.`adminCommentDate`,  0)  adminCommentDate,"
					+ "IFNULL(`attendance`.`supervisorCommentDate`,  0)  supervisorCommentDate,"
					+ "IFNULL(`attendance`.`meCommentDate`, 0)  meCommentDate " + "From  attendance where studentsubject = '"
					+ studentsubject + "' " + "and lower(studentschool) = '" + studentschool + "' "
					+ "and lower(studentregion) = '" + studentregion + "' " + "and lower(studentform) like '"
					+ studentform + "%' " + "and deviceActionformattedDate between '" + fromDate + "' and '" + toDate
					+ "' " + "and flag = 'New'  order by dateOfImport desc;");

			// execute query and get result list
			List<Object[]> rows = theQuery.getResultList();

			for (Object[] row : rows) {

				Attendance stat = new Attendance();

				stat.setId((int) (row[0]));
				stat.setDeviceActionformattedDate(row[1].toString());
				stat.setDeviceActionandroid_id(row[2].toString());
				stat.setDeviceActionusername(row[3].toString());
				stat.setDeviceActionuserrole(row[4].toString());
				stat.setDeviceActionlatValue(row[5].toString());
				stat.setDeviceActionlngValue(row[6].toString());
				stat.setDeviceActionactions(row[7].toString());
				stat.setTeacherusername1t(row[8].toString());
				stat.setTeacherpint(row[9].toString());
				stat.setTeacherfirstnamet(row[10].toString());
				stat.setTeachersecondnamet(row[11].toString());
				stat.setTeachersurnamet(row[12].toString());
				stat.setTeacherschoolt(row[13].toString());
				stat.setDateOfAttendance(row[14].toString());
				stat.setDateOfImport(row[15].toString());
				stat.setStudentpin(row[16].toString());
				stat.setStudentfirstname(row[17].toString());
				stat.setStudentregion(row[18].toString());
				stat.setStudentsurname(row[19].toString());
				stat.setStudentschool(row[20].toString());
				stat.setStudentgender(row[21].toString());
				stat.setStudentdateofbirth(row[22].toString());
				stat.setStudentform(row[23].toString());
				stat.setStudentstatus(row[24].toString());
				stat.setStudentsubject(row[25].toString());
				stat.setStudentattendance(row[26].toString());
				stat.setFlag(row[27].toString());
				stat.setMeComment(row[28].toString());
				stat.setMeOfficer(row[29].toString());
				stat.setSupervisor(row[30].toString());
				stat.setAdmin(row[31].toString());
				stat.setAdminComment(row[32].toString());
				stat.setSupervisorComment(row[33].toString());
				stat.setAdminCommentDate(row[34].toString());
				stat.setSupervisorCommentDate(row[35].toString());
				stat.setMeCommentDate(row[36].toString());

				attendance.add(new Attendance(

						stat.getId(), stat.getDeviceActionformattedDate().toString(),
						stat.getDeviceActionandroid_id().toString(), stat.getDeviceActionusername().toString(),
						stat.getDeviceActionuserrole().toString(), stat.getDeviceActionlatValue().toString(),
						stat.getDeviceActionlngValue().toString(), stat.getDeviceActionactions().toString(),
						stat.getTeacherusername1t().toString(), stat.getTeacherpint().toString(),
						stat.getTeacherfirstnamet().toString(), stat.getTeachersecondnamet().toString(),
						stat.getTeachersurnamet().toString(), stat.getTeacherschoolt().toString(),
						stat.getDateOfAttendance().toString(), stat.getDateOfImport().toString(),
						stat.getStudentpin().toString(), stat.getStudentfirstname().toString(),
						stat.getStudentregion().toString(), stat.getStudentsurname().toString(),
						stat.getStudentschool().toString(), stat.getStudentgender().toString(),
						stat.getStudentdateofbirth().toString(), stat.getStudentform().toString(),
						stat.getStudentstatus().toString(), stat.getStudentsubject().toString(),
						stat.getStudentattendance().toString(), stat.getFlag().toString(),
						stat.getMeCommentDate().toString(), stat.getMeOfficer().toString(),
						stat.getSupervisor().toString(), stat.getAdmin().toString(), stat.getAdminComment().toString(),
						stat.getSupervisorComment().toString(), stat.getAdminCommentDate().toString(),
						stat.getSupervisorCommentDate().toString(), stat.getMeCommentDate().toString()

				));
			}

		}
		// return the results
		return attendance;

	}

	@Override
	public void pendAttendanceMe(Attendance theAttendance) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(theAttendance);

	}

	@Override
	public List<Attendance> getMePendingAttendance() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and flag = 'Pending' order by dateOfImport desc",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	@Override
	public List<Attendance> getMePendingDg() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject = 'DG Packs' and flag = 'Pending' order by dateOfImport desc",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	@Override
	public List<String> getMeSubjects() {

		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name

		SQLQuery theQuery = currentSession.createSQLQuery(
				"Select Distinct(studentsubject) from attendance where studentsubject != 'DG Packs' order by case when studentsubject like 'Form One%' then 0 when studentsubject like 'Form Two%' then 1 when studentsubject like 'Form Thre%' then 2 when studentsubject like 'Form Four%' then 3 when studentsubject like 'Form Five%' then 4 else 5 end, studentsubject asc;");

		// execute query and get result list
		List<String> theSubjects = theQuery.getResultList();

		// return the results
		return theSubjects;
	}

	////// supervisor

	@Override
	public List<Attendance> getSuAttendance() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and flag = 'Verified' order by dateOfImport desc",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	@Override
	public List<Attendance> searchSuAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate, String studentsubject) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Attendance> theQuery = null;

		if (studentschool != null && studentregion != null && studentform != null && fromDate != null
				&& toDate != null) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Attendance where studentsubject = :theStudentsubject and lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate and flag = 'Verified' order by dateOfImport desc",
					Attendance.class);
			theQuery.setParameter("theStudentschool", studentschool);
			theQuery.setParameter("theStudentregion", studentregion);
			theQuery.setParameter("theStudentform", studentform + "%");
			theQuery.setParameter("theFromDate", fromDate);
			theQuery.setParameter("theToDate", toDate);
			theQuery.setParameter("theStudentsubject", studentsubject);

			// System.out.println("Formatted Date is: " + startDate.toString());

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Attendance where studentsubject != 'DG Packs' ",
					Attendance.class);
		}

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	@Override
	public void approveAllSu(String studentschool, String studentregion, String studentform, String fromDate,
			String toDate, String studentsubject) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery(
				"update Attendance set flag = 'Approved' where studentsubject = :theStudentsubject and lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("theStudentschool", studentschool);
		theQuery.setParameter("theStudentregion", studentregion);
		theQuery.setParameter("theStudentform", studentform + "%");
		theQuery.setParameter("theFromDate", fromDate);
		theQuery.setParameter("theToDate", toDate);
		theQuery.setParameter("theStudentsubject", studentsubject);

		theQuery.executeUpdate();

	}

	@Override
	public Attendance getAttendance(int theId) {

		// get the current hibernate session

		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Attendance theAttendance = currentSession.get(Attendance.class, theId);

		return theAttendance;

	}

	@Override
	public void saveAttendance(Attendance theAttendance) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		int theId = theAttendance.getId();

		String meComment = theAttendance.getMeComment();

		Calendar calobj = Calendar.getInstance();
		String meCommentDate = calobj.toString();

		// create a query ... sort by last name
		Query theQuery = currentSession.createQuery("update Attendance set flag = 'Pending', "
				+ "meComment = :theMeComment," + "meCommentDate = :theMeCommentDate where id = :attendanceId");
		// theQuery.setParameter("flagName", "Disqualified");
		theQuery.setParameter("meCommentDate", meCommentDate);
		theQuery.setParameter("meComment", meComment);
		theQuery.setParameter("attendanceId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public void resetSuAttendanceMe(int theId, String supervisorComment, String supervisorCommentDate) {
		// TODO Auto-generated method stub

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();

		supervisorCommentDate = dateobj.toString();

		// create a query ... sort by last name
		Query theQuery = currentSession
				.createQuery("update Attendance set flag = 'New', " + "supervisorComment = :theSupervisorComment,"
						+ "supervisorCommentDate = :theSupervisorCommentDate where id = :attendanceId");

		theQuery.setParameter("theSupervisorComment", supervisorComment);
		theQuery.setParameter("theSupervisorCommentDate", supervisorCommentDate);
		theQuery.setParameter("attendanceId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public List<Attendance> getResetMeAttendance() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and studentform != 'Form' and flag = 'New' and supervisorComment not like '%NONE%' order by dateOfImport desc, studentschool, studentform,studentsubject",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public List<Attendance> getMeDg() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession
				.createQuery("from Attendance where studentsubject = 'DG Packs' and flag = 'New' ", Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public List<Attendance> getSuDg() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject = 'DG Packs' and flag = 'Verified' order by dateOfImport desc",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public List<Attendance> getApproved() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and flag = 'Approved' order by dateOfImport desc, studentschool, studentform,studentsubject",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public List<Attendance> getVerified() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and studentsubject != 'Subject' and flag = 'Verified' order by dateOfImport desc, studentschool, studentform,studentsubject",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	/////// report

	@Override
	public List<AttendanceStatistics> getAdminAttendanceCountPresent() {
		List<AttendanceStatistics> statistics = new ArrayList<AttendanceStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery("select "
				+ "deviceActionformattedDate ,studentregion, studentschool, studentgender, studentform, studentstatus, "
				+ "studentsubject, studentattendance, count(flag) as flag " + "FROM attendance "
				+ "where studentform != 'Form' and studentsubject != 'DG Packs' and studentattendance = 'Present' "
				+ "group by deviceActionformattedDate,studentregion,studentschool,studentgender,studentform,studentstatus ,"
				+ "studentsubject ,studentattendance,flag "
				+ "order by  deviceActionformattedDate,studentregion, studentschool, studentform, studentstatus, studentsubject, "
				+ "studentattendance,studentgender");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceStatistics stat = new AttendanceStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentgender(row[3].toString());
			stat.setStudentform(row[4].toString());
			stat.setStudentstatus(row[5].toString());
			stat.setStudentsubject(row[6].toString());
			stat.setStudentattendance(row[7].toString());
			stat.setFlag(row[8].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentgender().toString(),
					stat.getStudentform().toString(), stat.getStudentstatus().toString(), studentsubject2,
					stat.getStudentattendance(), stat.getFlag().toString()

			));
		}

		// return the results
		return statistics;

	}

	@Override
	public List<AttendanceStatistics> getAdminAttendanceCountAbsent() {
		List<AttendanceStatistics> statistics = new ArrayList<AttendanceStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery("select "
				+ "deviceActionformattedDate ,studentregion, studentschool, studentgender, studentform, studentstatus, "
				+ "studentsubject, studentattendance, count(flag) as flag " + "FROM attendance "
				+ "where studentform != 'Form' and studentsubject != 'DG Packs' and studentattendance = 'Absent' "
				+ "group by deviceActionformattedDate,studentregion,studentschool,studentgender,studentform,studentstatus ,"
				+ "studentsubject ,studentattendance,flag "
				+ "order by  deviceActionformattedDate,studentregion, studentschool, studentform, studentstatus, studentsubject, "
				+ "studentattendance,studentgender");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceStatistics stat = new AttendanceStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentgender(row[3].toString());
			stat.setStudentform(row[4].toString());
			stat.setStudentstatus(row[5].toString());
			stat.setStudentsubject(row[6].toString());
			stat.setStudentattendance(row[7].toString());
			stat.setFlag(row[8].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentgender().toString(),
					stat.getStudentform().toString(), stat.getStudentstatus().toString(), studentsubject2,
					stat.getStudentattendance(), stat.getFlag().toString()

			));

			// System.out.println(stat);
		}

		// return the results
		return statistics;

	}

	@Override
	public List<Attendance> getSuAttendanceApproved() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<Attendance> theQuery = currentSession.createQuery(
				"from Attendance where studentsubject != 'DG Packs' and flag = 'Approved' order by dateOfImport desc, studentschool, studentform,studentsubject",
				Attendance.class);

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;

	}

	@Override
	public List<Attendance> approvedAttendance(String studentschool, String studentregion, String studentform,
			String fromDate, String toDate) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Attendance> theQuery = null;

		if (studentschool != null && studentregion != null && studentform != null && fromDate != null
				&& toDate != null) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Attendance where lower(studentschool) = :theStudentschool and lower(studentregion) = :theStudentregion and lower(studentform) like :theStudentform and deviceActionformattedDate between :theFromDate and :theToDate and flag = 'Approved' order by dateOfImport desc",
					Attendance.class);
			theQuery.setParameter("theStudentschool", studentschool);
			theQuery.setParameter("theStudentregion", studentregion);
			theQuery.setParameter("theStudentform", studentform + "%");
			theQuery.setParameter("theFromDate", fromDate);
			theQuery.setParameter("theToDate", toDate);

			// System.out.println("Formatted Date is: " + startDate.toString());

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Attendance where studentsubject != 'DG Packs' ",
					Attendance.class);
		}

		// execute query and get result list
		List<Attendance> attendance = theQuery.getResultList();

		// return the results
		return attendance;
	}

	@Override
	public List<AttendanceSubmissionStatistics> getAdminAttendanceCountSubmitted() {

		List<AttendanceSubmissionStatistics> statistics = new ArrayList<AttendanceSubmissionStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession
				.createSQLQuery("SELECT deviceActionformattedDate, studentregion, studentschool, "
						+ "studentform, studentsubject, Count(flag) as flag " + "FROM bantwanaclasstracker.attendance "
						+ "where studentform != 'Form' and studentsubject != 'DG Packs' "
						+ "group by deviceActionformattedDate, studentregion, studentschool, studentform, studentsubject "
						+ "order by  studentregion, studentschool, studentform,deviceActionformattedDate, studentsubject;");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceSubmissionStatistics stat = new AttendanceSubmissionStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentform(row[3].toString());
			stat.setStudentsubject(row[4].toString());
			stat.setFlag(row[5].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceSubmissionStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentform().toString(), studentsubject2,
					stat.getFlag().toString()

			));

			// System.out.println(stat);
		}

		// return the results
		return statistics;
	}

	@Override
	public List<AttendanceSubmissionStatistics> getAdminDgCountSubmitted() {

		List<AttendanceSubmissionStatistics> statistics = new ArrayList<AttendanceSubmissionStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession
				.createSQLQuery("SELECT deviceActionformattedDate, studentregion, studentschool, "
						+ "studentform, studentsubject, Count(flag) as flag " + "FROM bantwanaclasstracker.attendance "
						+ "where studentform != 'Form' and studentsubject = 'DG Packs' "
						+ "group by deviceActionformattedDate, studentregion, studentschool, studentform, studentsubject "
						+ "order by  studentregion, studentschool, studentform,deviceActionformattedDate, studentsubject;");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceSubmissionStatistics stat = new AttendanceSubmissionStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentform(row[3].toString());
			stat.setStudentsubject(row[4].toString());
			stat.setFlag(row[5].toString());

			statistics.add(new AttendanceSubmissionStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentform().toString(),
					stat.getStudentsubject().toString(), stat.getFlag().toString()

			));

		}

		// return the results
		return statistics;
	}

	@Override
	public List<String> getSchools() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<String> theQuery = currentSession
				.createQuery("Select Distinct studentschool from Attendance order by studentschool", String.class);

		// execute query and get result list
		List<String> theSchools = theQuery.getResultList();

		// return the results
		return theSchools;
	}

	@Override
	public List<String> getSubjects() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery(
				"Select Distinct(studentsubject) from attendance where studentsubject != 'DG Packs' "
				+ "order by case when studentsubject like 'Form One%' then 0 "
				+ "when studentsubject like 'Form Two%' then 1 "
				+ "when studentsubject like 'Form Thre%' then 2 "
				+ "when studentsubject like 'Form Four%' then 3 "
				+ "when studentsubject like 'Form Five%' then 4 else 5 end, studentsubject asc;");

		// execute query and get result list
		List<String> theSubjects = theQuery.getResultList();

		// return the results
		return theSubjects;
	}

	@Override
	public List<String> getClasses() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery("Select Distinct(studentform) from attendance where studentsubject != 'DG Packs' "
						+ "order by case when studentform like 'Form 1%' then 0 "
						+ "when studentform like 'Form 2%' then 1 "
						+ "when studentform like 'Form 3%' then 2 "
						+ "when studentform like 'Form 4%' then 3 "
						+ "when studentform like 'Form 5%' then 4 else studentform end, studentform asc;");

		// execute query and get result list
		List<String> theClasses = theQuery.getResultList();

		// return the results
		return theClasses;
	}

	@Override
	public List<String> getRegions() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		Query<String> theQuery = currentSession
				.createQuery("Select Distinct studentregion from Attendance order by studentregion", String.class);

		// execute query and get result list
		List<String> theRegions = theQuery.getResultList();

		// return the results
		return theRegions;
	}

	@Override
	public List<AttendanceStatistics> getAdminAttendanceSearchAbsent(String fromDate, String toDate,
			String studentschool) {
		List<AttendanceStatistics> statistics = new ArrayList<AttendanceStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery("select "
				+ "deviceActionformattedDate ,studentregion, studentschool, studentgender, studentform, studentstatus, "
				+ "studentsubject, studentattendance, count(flag) as flag " + "FROM attendance "
				+ "where studentform != 'Form' and studentsubject != 'DG Packs' and studentattendance = 'Absent' and studentschool = '"
				+ studentschool + "' and deviceActionformattedDate between '" + fromDate + "' and '" + toDate + "' "
				+ "group by deviceActionformattedDate,studentregion,studentschool,studentgender,studentform,studentstatus ,"
				+ "studentsubject ,studentattendance,flag " + "order by  deviceActionformattedDate,studentform;");

		// execute query and get result list

		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceStatistics stat = new AttendanceStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentgender(row[3].toString());
			stat.setStudentform(row[4].toString());
			stat.setStudentstatus(row[5].toString());
			stat.setStudentsubject(row[6].toString());
			stat.setStudentattendance(row[7].toString());
			stat.setFlag(row[8].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentgender().toString(),
					stat.getStudentform().toString(), stat.getStudentstatus().toString(), studentsubject2,
					stat.getStudentattendance(), stat.getFlag().toString()

			));
		}

		// return the results
		return statistics;
	}

	@Override
	public List<AttendanceStatistics> getAdminAttendanceSearchPresent(String fromDate, String toDate,
			String studentschool) {
		List<AttendanceStatistics> statistics = new ArrayList<AttendanceStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession.createSQLQuery("select "
				+ "deviceActionformattedDate ,studentregion, studentschool, studentgender, studentform, studentstatus, "
				+ "studentsubject, studentattendance, count(flag) as flag " + "FROM attendance "
				+ "where studentform != 'Form' and studentsubject != 'DG Packs' and studentattendance = 'Present' and studentschool = '"
				+ studentschool + "' and deviceActionformattedDate between '" + fromDate + "' and '" + toDate + "' "
				+ "group by deviceActionformattedDate,studentregion,studentschool,studentgender,studentform,studentstatus ,"
				+ "studentsubject ,studentattendance,flag " + "order by  deviceActionformattedDate,studentform;");

		// execute query and get result list

		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceStatistics stat = new AttendanceStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentgender(row[3].toString());
			stat.setStudentform(row[4].toString());
			stat.setStudentstatus(row[5].toString());
			stat.setStudentsubject(row[6].toString());
			stat.setStudentattendance(row[7].toString());
			stat.setFlag(row[8].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentgender().toString(),
					stat.getStudentform().toString(), stat.getStudentstatus().toString(), studentsubject2,
					stat.getStudentattendance(), stat.getFlag().toString()

			));
		}

		// return the results
		return statistics;
	}

	@Override
	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionApproved() {

		List<AttendanceSubmissionStatistics> statistics = new ArrayList<AttendanceSubmissionStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession
				.createSQLQuery("SELECT deviceActionformattedDate, studentregion, studentschool, "
						+ "studentform, studentsubject, Count(flag) as flag " + "FROM bantwanaclasstracker.attendance "
						+ "where studentform != 'Form' and studentsubject != 'DG Packs' and flag='Approved'"
						+ "group by deviceActionformattedDate, studentregion, studentschool, studentform, studentsubject "
						+ "order by  studentregion, studentschool, studentform,deviceActionformattedDate, studentsubject;");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceSubmissionStatistics stat = new AttendanceSubmissionStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentform(row[3].toString());
			stat.setStudentsubject(row[4].toString());
			stat.setFlag(row[5].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceSubmissionStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentform().toString(), studentsubject2,
					stat.getFlag().toString()

			));

			// System.out.println(stat);
		}

		// return the results
		return statistics;
	}

	@Override
	public List<AttendanceSubmissionStatistics> getAdminAttendanceSubmissionVeified() {
		List<AttendanceSubmissionStatistics> statistics = new ArrayList<AttendanceSubmissionStatistics>();

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query ... sort by last name
		SQLQuery theQuery = currentSession
				.createSQLQuery("SELECT deviceActionformattedDate, studentregion, studentschool, "
						+ "studentform, studentsubject, Count(flag) as flag " + "FROM bantwanaclasstracker.attendance "
						+ "where studentform != 'Form' and studentsubject != 'DG Packs' and flag='Verified'"
						+ "group by deviceActionformattedDate, studentregion, studentschool, studentform, studentsubject "
						+ "order by  studentregion, studentschool, studentform,deviceActionformattedDate, studentsubject;");

		// execute query and get result list
		List<Object[]> rows = theQuery.getResultList();

		for (Object[] row : rows) {
			AttendanceSubmissionStatistics stat = new AttendanceSubmissionStatistics();

			stat.setDeviceActionformattedDate(row[0].toString());
			stat.setStudentregion(row[1].toString());
			stat.setStudentschool(row[2].toString());
			stat.setStudentform(row[3].toString());
			stat.setStudentsubject(row[4].toString());
			stat.setFlag(row[5].toString());

			if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {
				studentsubject2 = "Form 1 - IGC";
			}

			if (stat.getStudentsubject().toString().equals("Form One: Test")) {
				studentsubject2 = "Form 1 - Test";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Getting Used to your New School (GUYNS)")) {
				studentsubject2 = "Form 1 - GUYNS";
			}

			else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 1 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Developing Self-Awareness (DSA)")) {
				studentsubject2 = "Form 1 - DSA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Peer Pressure (PP)")) {
				studentsubject2 = "Form 1 - PP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 1 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Decision Making (DM)")) {
				studentsubject2 = "Form 1 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Time Management  (TMGT)")) {
				studentsubject2 = "Form 1 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 1 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Societal Norms and Values (SNV)")) {
				studentsubject2 = "Form 1 - SNV";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - Dealing With Loss (DWL)")) {
				studentsubject2 = "Form 1 - DWL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Physical Body Changes Associated with Puberty (PBCAP)")) {
				studentsubject2 = "Form 1 - PBCAP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Social and Emotional Changes at Puberty (SECP)")) {
				studentsubject2 = "Form 1 - SECP";
			} else if (stat.getStudentsubject().toString().equals("Form One: Health Promotion - Sexual Abuse (SA)")) {
				studentsubject2 = "Form 1 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Health Promotion - Reporting Sexual Abuse (RSA)")) {
				studentsubject2 = "Form 1 - RSA";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS -  (HIVA)")) {
				studentsubject2 = "Form 1 - HIVA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Drivers of HIV in Swaziland (DHS)")) {
				studentsubject2 = "Form 1 - DHS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Prevention of HIV Infections (PHI)")) {
				studentsubject2 = "Form 1 - PHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form One: HIV and AIDS - Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 1 - HSB";
			} else if (stat.getStudentsubject().toString().equals("Form One: HIV and AIDS - Positive Living (PL)")) {
				studentsubject2 = "Form 1 - PL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Communication Skills (CS)")) {
				studentsubject2 = "Form 2 - CS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Assertiveness (ASS)")) {
				studentsubject2 = "Form 2 - ASS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Effective Study Skills (ESS)")) {
				studentsubject2 = "Form 2 - ESS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 2 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Guidance and Counselling - Seeking Financial Assistance (SFA)")) {
				studentsubject2 = "Form 2 - SFA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Primary Stage of HIV Infection (PSHI)")) {
				studentsubject2 = "Form 2 - PSHI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Immune Response to HIV (IRH)")) {
				studentsubject2 = "Form 2 - IRH";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Two: Health Promotion - Signs and Symptoms of Sexually Transmitted Infections (SSSTI)")) {
				studentsubject2 = "Form 2 - SSSTI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Intergenerational Sex (IGS)")) {
				studentsubject2 = "Form 2 - IGS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Transactional Sex (TAS)")) {
				studentsubject2 = "Form 2 - TAS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 2 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS -  Environmental Cleanliness (ENC)")) {
				studentsubject2 = "Form 2 - ENC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Hygiene and Infectious Diseases (HID)")) {
				studentsubject2 = "Form 2 - HID";
			} else if (stat.getStudentsubject().toString().equals("Form Two: HIV and AIDS - Early Parenthood (EPHD)")) {
				studentsubject2 = "Form 2 - EPHD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Two: HIV and AIDS - Coping with Alcohol & Substance Abusers (CASA)")) {
				studentsubject2 = "Form 2 - CASA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Transition Learning (TL)")) {
				studentsubject2 = "Form 3 - TL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Life (CL)")) {
				studentsubject2 = "Form 3 - CL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Job-Seeking Skills (JSS)")) {
				studentsubject2 = "Form 3 - JSS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Entrepreneurship Skills (ES)")) {
				studentsubject2 = "Form 3 - ES";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - The Work Place (TWP)")) {
				studentsubject2 = "Form 3 - TWP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Self Awareness (SA)")) {
				studentsubject2 = "Form 3 - SA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Dealing with Emotions (DWE)")) {
				studentsubject2 = "Form 3 - DWE";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Time Management (TMGT)")) {
				studentsubject2 = "Form 3 - TMGT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Study Skills (SS)")) {
				studentsubject2 = "Form 3 - SS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Guidance and Counselling - Career Awareness (CA)")) {
				studentsubject2 = "Form 3 - CA";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Alcohol and Substance Abuse as Drivers of HIV (ASADH)")) {
				studentsubject2 = "Form 3 - ASADH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - Multiple and Concurrent Partners (MCP)")) {
				studentsubject2 = "Form 3 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: Health Promotion - International Sex  (IS)")) {
				studentsubject2 = "Form 3 - IS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS -  Health Seeking Behaviours (HSB)")) {
				studentsubject2 = "Form 3 - HSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Three: HIV and AIDS - Nutrition and HIV (NH)")) {
				studentsubject2 = "Form 3 - NH";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Transition to Senior Secondary Learning (TSSL)")) {
				studentsubject2 = "Form 4 - TSSL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Self-Concept (SC)")) {
				studentsubject2 = "Form 4 - SC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Decision Making (DM) ")) {
				studentsubject2 = "Form 4 - DM";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Critical Thinking (CT)")) {
				studentsubject2 = "Form 4 - CT";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Guidance and Counselling - Goal Setting (GS)")) {
				studentsubject2 = "Form 4 - GS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Antiretroviral Treatment (ART)")) {
				studentsubject2 = "Form 4 - ART";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Delaying Sexual Debut (DSD)")) {
				studentsubject2 = "Form 4 - DSD";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Multiple Concurrent Partnership (MCP)")) {
				studentsubject2 = "Form 4 - MCP";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: Health Promotion - Responsible Sexual Behaviour (RSB)")) {
				studentsubject2 = "Form 4 - RSB";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Opportunistic Infections (OPI)")) {
				studentsubject2 = "Form 4 - OPI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Exploring Issues of Sexual Orientation (EISO)")) {
				studentsubject2 = "Form 4 - EISO";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Four: HIV and AIDS - Consequences of Sexual Abuse  (CSA)")) {
				studentsubject2 = "Form 4 - CSA";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Preparing for Transition to Higher Education and Place of Work (PTHEPW)")) {
				studentsubject2 = "Form 5 - PTHEPW";
			} else if (stat.getStudentsubject().toString().equals(
					"Form Five: Guidance and Counselling - Higher Education and Training Institutions (HETI)")) {
				studentsubject2 = "Form 5 - HETI";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Scholarship / Bursary (S/B)")) {
				studentsubject2 = "Form 5 - S/B";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Work Ethics  (WE)")) {
				studentsubject2 = "Form 5 - WE";
			} else if (stat.getStudentsubject().toString().equals("Money Management Skills (MMS)")) {
				studentsubject2 = "Form 5 - MMS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Labour Laws (LL)")) {
				studentsubject2 = "Form 5 - LL";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Health Promotion - HIV Testing and Counselling  (HTC)")) {
				studentsubject2 = "Form 5 - HTC";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Commonly Abused Drugs and Substances (CADS)")) {
				studentsubject2 = "Form 5 - CADS";
			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: HIV and AIDS - Communicable and Non Communicable Diseases (CNCD)")) {
				studentsubject2 = "Form 5 - CNCD";
				///// missing

			} else if (stat.getStudentsubject().toString()
					.equals("Form Five: Guidance and Counselling - Money Management Skills (MMS)")) {

				studentsubject2 = "Form 5 - MMS";

			} else if (stat.getStudentsubject().toString()
					.equals("Form One: Guidance and Counselling - The Importance of Guidance and Counselling (IGC)")) {

				studentsubject2 = "Form 1 - IGC";

			} else {

				studentsubject2 = stat.getStudentsubject().toString();
			}
			statistics.add(new AttendanceSubmissionStatistics(

					stat.getDeviceActionformattedDate().toString(), stat.getStudentregion().toString(),
					stat.getStudentschool().toString(), stat.getStudentform().toString(), studentsubject2,
					stat.getFlag().toString()

			));

			// System.out.println(stat);
		}

		// return the results
		return statistics;
	}

}
